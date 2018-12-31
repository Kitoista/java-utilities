package ui.component.bind.imp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import geometry.Dimensions;
import geometry.Positioner;
import geometry.Resizer;
import image.Images;
import meta.Property;
import ui.component.Components;
import ui.component.FileChooser;
import ui.component.bind.BindedProperty;
import ui.component.converter.BufferedImageDisplayer;
import ui.frame.Frames;
import ui.frame.Show;

public class BindedBufferedImage extends BindedProperty<Object> {

	private static final long serialVersionUID = 1L;
	private static int imgHeight = 48;
	
	private static float maxImgWidthRatio = 0.75f;
	private static float maxImgHeightRatio = 0.9f;
	
	protected JLabel label;
	protected Component image;
	protected JButton showBtn;
	protected JButton saveBtn;
	protected JButton loadBtn;
	
	protected JPanel center;
	protected JPanel east;
	protected JPanel west;
	
	private Show show;
	
	protected BindedBufferedImage(Property property, Object object) {
		super(property, object);
	}
	
	@Override
	protected void initGui() {
		this.setLayout(new BorderLayout());
		
		initPanels();
		
		label = new JLabel(property.getName());
		showBtn = new JButton("show");
		saveBtn = new JButton("save");
		loadBtn = new JButton("load");
		
		showBtn.addActionListener(e -> onShow());
		saveBtn.addActionListener(e -> onSave());
		loadBtn.addActionListener(e -> onLoad());
		
		loadBtn.setEnabled(property.hasSet());
		
		west.add(label);
		east.add(showBtn);
		east.add(saveBtn);
		east.add(loadBtn);
		
		redraw();
	}
	
	private void initPanels() {
		center = new JPanel();
		west = new JPanel();
		east = new JPanel();
		
		this.add(center, BorderLayout.CENTER);
		this.add(west, BorderLayout.WEST);
		this.add(east, BorderLayout.EAST);
	}
	
	private void redraw() {
		center.removeAll();
		try {
			if (object != null) {
				BufferedImage img = (BufferedImage) property.get(object);
				if (img != null) {
					image = new BufferedImageDisplayer().toComponent(img, new Dimension(imgHeight * img.getWidth() / img.getHeight(), imgHeight));
					center.add(image);
				} else {
					center.add(Images.placeholderComponent(imgHeight, imgHeight, "image"));
				}
			}
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			e.printStackTrace();
		}
		JFrame parentFrame = Components.getParentFrame(this);
		if (parentFrame != null) {
			parentFrame.repaint();
			parentFrame.pack();
		}
		
	}
	
	private void onSave() {
		if (object == null) return;
		try {
			Object obj = property.get(object);
			if (obj == null) return;
			BufferedImage img = (BufferedImage) obj;
			File file = FileChooser.getFile();
			if (file == null) return;
			String[] parts = file.getName().split("\\.");
			if (parts.length == 1) {
				file = new File(file + ".png");
			}
			ImageIO.write(img, "png", file);
		} catch (IllegalArgumentException | ReflectiveOperationException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void onLoad() {
		if (object == null) return;
		try {
			File file = FileChooser.getFile();
			if (file == null) return;
			BufferedImage img = ImageIO.read(file);
			if (img == null) return;
			property.set(object, img);
		} catch (IllegalArgumentException | ReflectiveOperationException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void onShow() {
		if (object == null) return;
		if (show == null) {
			show = new Show();
			show.getFrame().setVisible(false);
		}
		try {
			BufferedImage img = (BufferedImage) property.get(object);
			if (img != null) {
				if (show != null) {
					show.getFrame().dispose();
				}
				show = new Show();
				Point location = MouseInfo.getPointerInfo().getLocation();
				Dimension screenSize = Frames.getMaximalizedSize();
				Dimension frameBorder = Dimensions.sub(show.getFrame().getSize(), show.getFrame().getContentPane().getSize());
				Dimension maxImgSize = Dimensions.sub(screenSize, frameBorder);
				maxImgSize.width *= maxImgWidthRatio;
				maxImgSize.height *= maxImgHeightRatio;
				img = Images.resize(img, Resizer.inside(new Dimension(img.getWidth(), img.getHeight()), maxImgSize));
				show.image(img);
				show.getFrame().setLocation(Positioner.placeInside(location, show.getFrame().getSize(), screenSize));
			}
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onMemChanged(Object obj) {
		redraw();
		super.onMemChanged(obj);
	}
	
	public static boolean canHandle(Class<?> type) {
		return BufferedImage.class.isAssignableFrom(type);
	}
	
}

package crazy.tools;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 图片操作工具类，如屏幕截图、合并图片等
 * 
 * @author Tom
 */
public class ImageUtils {
	private static final GraphicsDevice graphicsDevice = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private static final String imgType = "png";
	private static Robot awtRobot;
	static {
		try {
			awtRobot = new Robot(graphicsDevice);
		} catch (AWTException e) {

		}
	}

	/**
	 * 获取整个桌面的截图
	 * 
	 * @param destFile
	 *            图片保存到此文件中
	 * @return destFile对象
	 */
	public static File captureImage(File destFile) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(dimension);
		return captureImage(destFile, rectangle);
	}

	/**
	 * 获取指定区域的截图
	 * 
	 * @param destFile
	 *            图片保存到此文件中
	 * @param x
	 *            截图起始点位置横坐标
	 * @param y
	 *            截图起始点位置纵坐标
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @return destFile对象
	 */
	public static File captureImage(File destFile, int x, int y, int width,
			int height) {
		Rectangle rectangle = new Rectangle(x, y, width, height);
		return captureImage(destFile, rectangle);
	}

	/**
	 * 获取指定区域的截图
	 * 
	 * @param destFile
	 *            图片保存到此文件中
	 * @param rect
	 *            截图区域对象
	 * @return destFile对象
	 */
	public static File captureImage(File destFile, Rectangle rectangle) {
		try {
			BufferedImage desktopImg = awtRobot.createScreenCapture(rectangle);
			ImageIO.write(desktopImg, imgType, destFile);
			return destFile;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 将多张图片合并成一张图片，纵向合并
	 * 
	 * @param imgs
	 *            图片源
	 * @param destFile
	 *            图片保存到此文件中
	 */
	public static void mergeImages(List<File> imgList, File destFile) {
		if (imgList == null || imgList.size() < 1) {
			throw new RuntimeException("传入的文件集合为空");
		}
		int length = imgList.size();
		BufferedImage[] images = new BufferedImage[length];
		int[][] imgArrays = new int[length][];
		for (int i = 0; i < length; i++) {
			try {
				images[i] = ImageIO.read(imgList.get(i));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			int width = images[i].getWidth();
			int height = images[i].getHeight();
			imgArrays[i] = new int[width * height];// 从图片中读取RGB
			imgArrays[i] = images[i].getRGB(0, 0, width, height, imgArrays[i],
					0, width);
		}
		// 调整宽度、高度
		int destHeight = 0;
		int destWidth = images[0].getWidth();
		for (int i = 0; i < images.length; i++) {
			destWidth = destWidth > images[i].getWidth() ? destWidth
					: images[i].getWidth();
			destHeight += images[i].getHeight();
		}

		if (destHeight < 1) {
			throw new RuntimeException("图片总高度小于1，无法生成图片。");
		}

		// 生成新图片
		try {
			BufferedImage imgNew = new BufferedImage(destWidth, destHeight,
					BufferedImage.TYPE_INT_RGB);
			int height_i = 0;
			for (int i = 0; i < images.length; i++) {
				imgNew.setRGB(0, height_i, images[i].getWidth(),
						images[i].getHeight(), imgArrays[i], 0,
						images[i].getWidth());
				height_i += images[i].getHeight();
			}
			ImageIO.write(imgNew, imgType, destFile);// 写图片
		} catch (Exception ex) {
			throw new RuntimeException("合并图片出错：", ex);
		}
	}

	/**
	 * 将多张图片合并成一张图片，纵向合并
	 * 
	 * @param imgs
	 *            图片源
	 * @param destFile
	 *            生成的新图片
	 */
	public static void mergeImages(File[] imgArr, File destFile) {
		if (imgArr == null || imgArr.length < 1) {
			throw new RuntimeException("传入的文件集合为空");
		}
		mergeImages(Arrays.asList(imgArr), destFile);
	}

	/**
	 * 使用示例
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 截一张图
		File file1 = captureImage(new File("d:\\abc1.png"), 0, 0, 300, 200);
		// 再截一张图
		File file2 = captureImage(new File("d:\\abc2.png"), 0, 200, 350, 200);
		// 合并图片
		mergeImages(new File[] { file1, file2 }, new File("d:\\abc3.png"));
	}

}


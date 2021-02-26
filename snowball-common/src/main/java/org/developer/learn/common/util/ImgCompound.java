package org.developer.learn.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * @ClassName ImgCompound
 * @Description:图片合成
 * @Author lfq
 * @Date 2021/2/26
 **/
public class ImgCompound {

    /**
     * 合成图片
     *
     * @param backgroundPath
     * @param qrCodePath
     * @param message
     * @param outPutPath
     * @throws IOException
     */
    public static void overlapImage(String backgroundPath, String qrCodePath, String message, String outPutPath) throws Exception {

        // 设置背景图片大小
        File backFile = getFile(backgroundPath);
        File qrFile = getFile(qrCodePath);

        BufferedImage backgroundImage = resizeImage(680, 290, ImageIO.read(backFile));
        // 设置二维码图片大小
        BufferedImage qrCodeImage = resizeImage(136, 136, ImageIO.read(qrFile));

        Graphics2D graphics = backgroundImage.createGraphics();

        // 在背景图片上添加文字
        graphics.setColor(Color.black);
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 10));
        graphics.setColor(Color.red);
        graphics.drawString("毛豆医生", 150, 40);
        graphics.setColor(Color.gray);
        graphics.drawString("内分泌科", 200, 40);
        graphics.setColor(Color.green);
        graphics.drawString("杭州市第一人民医院", 150, 60);

        // 在背景图片上添加二维码图片
        graphics.drawImage(qrCodeImage, 67, 72, qrCodeImage.getWidth(), qrCodeImage.getHeight(), null);
        graphics.dispose();

        // 输出新的图片
        ImageIO.write(backgroundImage, "png", new File(outPutPath));
    }

    /**
     * 重新设置图片大小
     *
     * @param width
     * @param height
     * @param bufferedImage
     * @return
     */
    private static BufferedImage resizeImage(int width, int height, BufferedImage bufferedImage) {
        BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        newBufferedImage.getGraphics().drawImage(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        return newBufferedImage;
    }

    private static File getFile(String url) throws Exception {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("."));
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void main(String[] args) throws Exception {
        String backgroundPath = "https://tva1.sinaimg.cn/large/008eGmZEly1go10zoydiqj30iw082jrn.jpg";
        String qrCodePath = "https://tva1.sinaimg.cn/large/008eGmZEly1go110cwys7j303s03saa2.jpg";
        String message = "java菜市场-专注于技术干货的免费分享";
        String outPutPath = "/Users/lfq/Desktop/newImage.png";
        overlapImage(backgroundPath, qrCodePath, message, outPutPath);
    }
}

/*  1:   */ package com.gmail.filoghost.holographicdisplays.util;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.exception.UnreadableImageException;
/*  4:   */ import java.awt.image.BufferedImage;
/*  5:   */ import java.io.BufferedReader;
/*  6:   */ import java.io.File;
/*  7:   */ import java.io.FileNotFoundException;
/*  8:   */ import java.io.FileReader;
/*  9:   */ import java.io.IOException;
/* 10:   */ import java.net.URL;
/* 11:   */ import java.util.ArrayList;
/* 12:   */ import java.util.List;
/* 13:   */ import javax.imageio.ImageIO;
/* 14:   */ 
/* 15:   */ public class FileUtils
/* 16:   */ {
/* 17:   */   public static List<String> readLines(File file)
/* 18:   */     throws IOException, Exception
/* 19:   */   {
/* 20:22 */     if (!file.isFile()) {
/* 21:23 */       throw new FileNotFoundException(file.getName());
/* 22:   */     }
/* 23:26 */     BufferedReader br = null;
/* 24:   */     try
/* 25:   */     {
/* 26:30 */       List<String> lines = new ArrayList();
/* 27:32 */       if (!file.exists()) {
/* 28:33 */         throw new FileNotFoundException();
/* 29:   */       }
/* 30:36 */       br = new BufferedReader(new FileReader(file));
/* 31:37 */       String line = br.readLine();
/* 32:39 */       while (line != null)
/* 33:   */       {
/* 34:40 */         lines.add(line);
/* 35:41 */         line = br.readLine();
/* 36:   */       }
/* 37:44 */       return lines;
/* 38:   */     }
/* 39:   */     finally
/* 40:   */     {
/* 41:47 */       if (br != null) {
/* 42:   */         try
/* 43:   */         {
/* 44:49 */           br.close();
/* 45:   */         }
/* 46:   */         catch (IOException localIOException1) {}
/* 47:   */       }
/* 48:   */     }
/* 49:   */   }
/* 50:   */   
/* 51:   */   public static BufferedImage readImage(File file)
/* 52:   */     throws UnreadableImageException, IOException, Exception
/* 53:   */   {
/* 54:57 */     if (!file.isFile()) {
/* 55:58 */       throw new FileNotFoundException(file.getName());
/* 56:   */     }
/* 57:61 */     BufferedImage image = ImageIO.read(file);
/* 58:63 */     if (image == null) {
/* 59:64 */       throw new UnreadableImageException();
/* 60:   */     }
/* 61:67 */     return image;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public static BufferedImage readImage(URL url)
/* 65:   */     throws UnreadableImageException, IOException, Exception
/* 66:   */   {
/* 67:72 */     BufferedImage image = ImageIO.read(url);
/* 68:74 */     if (image == null) {
/* 69:75 */       throw new UnreadableImageException();
/* 70:   */     }
/* 71:78 */     return image;
/* 72:   */   }
/* 73:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.util.FileUtils
 * JD-Core Version:    0.7.0.1
 */
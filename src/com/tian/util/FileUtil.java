/*    */ package com.tian.util;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FileUtil
/*    */ {
/*    */   public static boolean createFolder(File file)
/*    */   {
/* 12 */     return file.mkdirs();
/*    */   }
/*    */ 
/*    */   public static List<String> getContentList(File file) throws Exception {
/* 16 */     BufferedReader reader = null;
/*    */     try {
/* 18 */       reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
/* 19 */       List list = new ArrayList();
/*    */ 
/* 21 */       String line = null;
/* 22 */       while ((line = reader.readLine()) != null)
/*    */       {
/* 24 */         list.add(line);
/*    */       }
/*    */ 
/* 27 */       return list;
/*    */     } finally {
/* 29 */       if (reader != null)
/* 30 */         reader.close();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     com.tian.util.FileUtil
 * JD-Core Version:    0.6.2
 */
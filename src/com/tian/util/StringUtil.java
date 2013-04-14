/*    */ package com.tian.util;
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   public static String orderXmlString(String xmlStr)
/*    */   {
/*  6 */     String re = xmlStr.replaceAll("<", "&lt;");
/*  7 */     re = re.replaceAll(">", "&gt;");
/*  8 */     re = re.replaceAll("&", "&amp;");
/*  9 */     re = re.replaceAll("'", "&apos;");
/* 10 */     re = re.replaceAll("\"", "&quot;");
/* 11 */     return re;
/*    */   }
/*    */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     com.tian.util.StringUtil
 * JD-Core Version:    0.6.2
 */
/*    */ package com.tian.test;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class TestXMLDocuemnt
/*    */ {
/*    */   public static void main(String[] a)
/*    */   {
/*  6 */     String fileName = "haha";
/*    */ 
/*  8 */     int dotIndex = fileName.lastIndexOf(".");
/*    */ 
/* 11 */     System.out.println(fileName.substring(0, dotIndex == -1 ? fileName.length() : dotIndex));
/*    */   }
/*    */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     com.tian.test.TestXMLDocuemnt
 * JD-Core Version:    0.6.2
 */
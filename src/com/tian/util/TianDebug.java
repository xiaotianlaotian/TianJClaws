/*   */ package com.tian.util;
/*   */ 
/*   */ import java.io.PrintStream;
/*   */ 
/*   */ public class TianDebug
/*   */ {
/* 5 */   public static boolean isDebug = true;
/*   */ 
/*   */   public static void debug(String info) {
/* 8 */     if (isDebug)
/* 9 */       System.out.println(info);
/*   */   }
/*   */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     com.tian.util.TianDebug
 * JD-Core Version:    0.6.2
 */
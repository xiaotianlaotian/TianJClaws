/*    */ import java.io.PrintStream;
/*    */ import java.util.StringTokenizer;
/*    */ 
/*    */ public class EstClaws
/*    */ {
/*    */   static
/*    */   {
/* 13 */     System.loadLibrary("EstClaws");
/*    */   }
/*    */ 
/*    */   public native int initAnalysis();
/*    */ 
/*    */   public native int finalAnalysis();
/*    */ 
/*    */   public native int makeAnalysis(String paramString);
/*    */ 
/*    */   public native String getAnalysisResult();
/*    */ 
/*    */   public String getTaggedText(String s)
/*    */   {
/* 29 */     int intInt = initAnalysis();
/* 30 */     if (intInt == -1)
/*    */     {
/* 32 */       System.err.println("initAnalysis failed");
/* 33 */       return null;
/*    */     }
/* 35 */     StringTokenizer stringtokenizer = new StringTokenizer(s, "\n");
/* 36 */     StringBuffer stringbuffer = new StringBuffer();
/* 37 */     while (stringtokenizer.hasMoreTokens())
/*    */     {
/* 39 */       String s1 = stringtokenizer.nextToken().trim();
/* 40 */       if (!s1.equals(""))
/*    */       {
/* 42 */         int makeInt = makeAnalysis(s1);
/* 43 */         if (makeInt == 0)
/*    */         {
/* 45 */           System.err.println("makeAnalysis failed");
/* 46 */           return null;
/*    */         }
/*    */         String s2;
/* 49 */         while ((s2 = getAnalysisResult()) != null)
/*    */         {
/*    */         //  String s2;
/* 50 */           if (s2 != "")
/* 51 */             stringbuffer.append(s2);
/*    */         }
/*    */       }
/*    */     }
/* 55 */     return stringbuffer.toString();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 61 */     EstClaws estclaws = new EstClaws();
/* 62 */     String str = estclaws.getTaggedText("I love you.");
/* 63 */     System.out.println(str);
/*    */   }
/*    */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     EstClaws
 * JD-Core Version:    0.6.2
 */
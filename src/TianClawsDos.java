/*    */ import com.tian.util.FileUtil;
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class TianClawsDos
/*    */ {
/*  7 */   public String version = "1.3";
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 13 */     ParameterParser pp = new ParameterParser();
/*    */ 
/* 15 */     File inputFileFolder = new File(pp.getInputPath());
/* 16 */     if ((!inputFileFolder.exists()) || (!inputFileFolder.isDirectory())) {
/* 17 */       throw new Exception("要处理的文件夹不存在，或者不是文件夹！");
/*    */     }
/*    */ 
/* 20 */     File outFileFolder = new File(pp.getOutputPath());
/*    */ 
/* 22 */     if (!outFileFolder.exists()) {
/* 23 */       FileUtil.createFolder(outFileFolder);
/*    */     }
/*    */ 
/* 27 */     ClawsBiz cb = new ClawsBiz(pp);
/* 28 */     cb.doClaws(inputFileFolder, outFileFolder);
/*    */ 
/* 30 */     System.out.println("Game Over!");
/*    */   }
/*    */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     TianClawsDos
 * JD-Core Version:    0.6.2
 */
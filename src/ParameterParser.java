/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class ParameterParser
/*     */ {
/*     */   public static final String IN_PATH = "inputPath";
/*     */   public static final String OUT_PATH = "outputPath";
/*     */   public static final String FILE_NAME_HEAD = "fileNameHead";
/*     */   public static final String FILE_NAME_FOOT = "fileNameFoot";
/*     */   public static final String IF_OUTPUT_XML = "ifOutputXml";
/*     */   public static final String OUTPUT_XML_ROOT = "outputXmlRoot";
/*     */   public static final String REPLACE_CHAR = "replaceChar";
/*  48 */   private String inputPath = null;
/*  49 */   private String outputPath = null;
/*  50 */   private String fileNameHead = null;
/*  51 */   private String fileNameFoot = null;
/*  52 */   private boolean ifOutputXml = false;
/*  53 */   private String outputXmlRoot = "artical";
/*     */ 
/*  55 */   private String replaceChar = null;
/*  56 */   private String[] replaceFrom = null;
/*  57 */   private String[] replaceTo = null;
/*     */ 
/*     */   public ParameterParser()
/*     */     throws Exception
/*     */   {
/*  64 */     InputStreamReader reader = new InputStreamReader(new FileInputStream(new File("conf" + File.separator + "init.properties")), "UTF-8");
/*  65 */     Properties pro = new Properties();
/*  66 */     pro.load(reader);
/*     */ 
/*  68 */     this.inputPath = pro.getProperty("inputPath");
/*  69 */     this.outputPath = pro.getProperty("outputPath");
/*  70 */     this.fileNameHead = pro.getProperty("fileNameHead");
/*  71 */     this.fileNameFoot = pro.getProperty("fileNameFoot");
/*     */ 
/*  73 */     this.ifOutputXml = (pro.getProperty("ifOutputXml").equals("true"));
/*  74 */     this.outputXmlRoot = pro.getProperty("outputXmlRoot");
/*     */ 
/*  76 */     if (!new File(this.inputPath).exists()) {
/*  77 */       throw new Exception("输入路径不正确，为找到文件！" + this.inputPath);
/*     */     }
/*  79 */     if (!new File(this.outputPath).exists()) {
/*  80 */       throw new Exception("输出路径不正确，为找到文件！" + this.outputPath);
/*     */     }
/*     */ 
/*  83 */     this.replaceChar = pro.getProperty("replaceChar");
/*  84 */     String[] chars = this.replaceChar.split("##");
/*  85 */     String reFrom = chars[0].trim().replace("#", "");
/*  86 */     String reTo = chars[1].trim().replace("#", "");
/*  87 */     this.replaceFrom = reFrom.split("\\|");
/*  88 */     this.replaceTo = reTo.split("\\|");
/*     */ 
/*  90 */     if (this.replaceFrom.length != this.replaceTo.length)
/*  91 */       throw new Exception("要替换的字符前后的个数不同，from的是 " + this.replaceFrom.length + " to是 " + this.outputPath);
/*     */   }
/*     */ 
/*     */   public String getInputPath()
/*     */   {
/*  96 */     return this.inputPath;
/*     */   }
/*     */   public void setInputPath(String inputPath) {
/*  99 */     this.inputPath = inputPath;
/*     */   }
/*     */   public String getOutputPath() {
/* 102 */     return this.outputPath;
/*     */   }
/*     */   public void setOutputPath(String outputPath) {
/* 105 */     this.outputPath = outputPath;
/*     */   }
/*     */ 
/*     */   public String getFileNameHead() {
/* 109 */     return this.fileNameHead;
/*     */   }
/*     */ 
/*     */   public void setFileNameHead(String fileNameHead) {
/* 113 */     this.fileNameHead = fileNameHead;
/*     */   }
/*     */ 
/*     */   public String getFileNameFoot() {
/* 117 */     return this.fileNameFoot;
/*     */   }
/*     */ 
/*     */   public void setFileNameFoot(String fileNameFoot) {
/* 121 */     this.fileNameFoot = fileNameFoot;
/*     */   }
/*     */ 
/*     */   public boolean isIfOutputXml() {
/* 125 */     return this.ifOutputXml;
/*     */   }
/*     */ 
/*     */   public void setIfOutputXml(boolean ifOutputXml) {
/* 129 */     this.ifOutputXml = ifOutputXml;
/*     */   }
/*     */ 
/*     */   public String getOutputXmlRoot() {
/* 133 */     return this.outputXmlRoot;
/*     */   }
/*     */ 
/*     */   public void setOutputXmlRoot(String outputXmlRoot) {
/* 137 */     this.outputXmlRoot = outputXmlRoot;
/*     */   }
/*     */ 
/*     */   public String[] getReplaceFrom() {
/* 141 */     return this.replaceFrom;
/*     */   }
/*     */ 
/*     */   public String[] getReplaceTo() {
/* 145 */     return this.replaceTo;
/*     */   }
/*     */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     ParameterParser
 * JD-Core Version:    0.6.2
 */
/*     */ import com.tian.model.TagSimpleEntry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class ClawsUtil
/*     */ {
/*     */   public static String vertToHoriz(String s)
/*     */   {
/*  22 */     String s3 = "";
/*  23 */     StringBuffer stringbuffer = new StringBuffer();
/*  24 */     StringTokenizer stringtokenizer2 = new StringTokenizer(s, "\n");
/*     */ 
/*  26 */     stringtokenizer2.nextToken();
/*  27 */     stringtokenizer2.nextToken();
/*  28 */     while (stringtokenizer2.hasMoreTokens())
/*     */     {
/*  30 */       String s2 = stringtokenizer2.nextToken().trim();
/*  31 */       if (!s2.equals(""))
/*     */       {
/*  33 */         StringTokenizer stringtokenizer1 = new StringTokenizer(s2, " \t");
/*  34 */         if (stringtokenizer1.countTokens() >= 3)
/*     */         {
/*  36 */           for (int j = 0; j < 3; j++) {
/*  37 */             s3 = stringtokenizer1.nextToken();
/*     */           }
/*  39 */           if (s3.startsWith("------")) {
/*  40 */             stringbuffer.append("\r\n");
/*     */           }
/*  42 */           else if ((!s3.startsWith("**")) || (s3.indexOf(";") <= 0))
/*     */           {
/*  44 */             stringbuffer.append(s3);
/*  45 */             s3 = stringtokenizer1.nextToken();
/*  46 */             if ((s3.equals(">")) || (s3.equals("<")) || (s3.startsWith("ERROR")))
/*  47 */               stringtokenizer1.nextToken();
/*  48 */             s3 = stringtokenizer1.nextToken();
/*  49 */             if (s3.charAt(0) == '[')
/*     */             {
/*  51 */               int l = s3.indexOf("/");
/*  52 */               s3 = s3.substring(1, l);
/*  53 */               if ((s3.endsWith("@")) || (s3.endsWith("%")))
/*  54 */                 s3 = s3.substring(0, s3.length() - 1);
/*     */             }
/*  56 */             stringbuffer.append("_" + s3 + " ");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  61 */     stringbuffer.append("\r\n");
/*     */ 
/*  63 */     return stringbuffer.toString();
/*     */   }
/*     */ 
/*     */   public static List<TagSimpleEntry> getTagEntryList(String s) {
/*  67 */     List list = new ArrayList();
/*  68 */     TagSimpleEntry entry = null;
/*     */ 
/*  70 */     String s3 = "";
/*  71 */     StringTokenizer stringtokenizer2 = new StringTokenizer(s, "\n");
/*     */ 
/*  73 */     stringtokenizer2.nextToken();
/*  74 */     stringtokenizer2.nextToken();
/*  75 */     while (stringtokenizer2.hasMoreTokens())
/*     */     {
/*  77 */       String s2 = stringtokenizer2.nextToken().trim();
/*  78 */       if (!s2.equals(""))
/*     */       {
/*  80 */         StringTokenizer stringtokenizer1 = new StringTokenizer(s2, " \t");
/*  81 */         if (stringtokenizer1.countTokens() >= 3)
/*     */         {
/*  83 */           for (int j = 0; j < 3; j++) {
/*  84 */             s3 = stringtokenizer1.nextToken();
/*     */           }
/*  86 */           if (!s3.startsWith("------"))
/*     */           {
/*  89 */             if ((!s3.startsWith("**")) || (s3.indexOf(";") <= 0))
/*     */             {
/*  91 */               entry = new TagSimpleEntry();
/*  92 */               entry.setTagEntry(s3);
/*     */ 
/*  94 */               s3 = stringtokenizer1.nextToken();
/*  95 */               if ((s3.equals(">")) || (s3.equals("<")) || (s3.startsWith("ERROR")))
/*  96 */                 stringtokenizer1.nextToken();
/*  97 */               s3 = stringtokenizer1.nextToken();
/*  98 */               if (s3.charAt(0) == '[')
/*     */               {
/* 100 */                 int l = s3.indexOf("/");
/* 101 */                 s3 = s3.substring(1, l);
/* 102 */                 if ((s3.endsWith("@")) || (s3.endsWith("%"))) {
/* 103 */                   s3 = s3.substring(0, s3.length() - 1);
/*     */                 }
/*     */               }
/* 106 */               entry.setTagPos(s3);
/*     */ 
/* 108 */               list.add(entry);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 114 */     return list;
/*     */   }
/*     */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     ClawsUtil
 * JD-Core Version:    0.6.2
 */
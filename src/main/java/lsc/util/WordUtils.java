package lsc.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lsc.bean.resume.Talent;
import lsc.bean.resume.TalentResumeCertification;
import lsc.bean.resume.TalentResumeDetailExp;
import lsc.bean.resume.TalentResumeEduExp;
import lsc.bean.resume.TalentResumeLanguageAbility;
import lsc.bean.resume.TalentResumeProjectExp;
import lsc.bean.resume.TalentResumeTrainExp;
import lsc.bean.resume.TalentResumeWorkExp;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.graphic.RtfShape;
import com.lowagie.text.rtf.graphic.RtfShapePosition;
import com.lowagie.text.rtf.graphic.RtfShapeProperty;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;

/**
 * @author liu
 */
public class WordUtils {
    private static final Logger logger = LoggerFactory.getLogger(WordUtils.class);

    // logo地址
    private static final String IMG_PATH = "image/logoSmall.jpg";
    // 自定义中文字体位置
    private static final String fontPath = "ttf/simsun.ttc";

    
    /********************************************************************************************
     * * * 使用Itext创建简历 * * * * *
     ********************************************************************************************/

    public static byte[] downResume(Map<String, Object> map) {
        return createTalent(map);
    }

    private static byte[] createTalent(Map<String, Object> talentMap) {
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            Document doc = new Document(PageSize.A4);
            Talent talent = null;
            if (MapUtils.isNotEmpty(talentMap)) {
                Object o = talentMap.get("talent");
                if (o instanceof Talent) {
                    talent = (Talent) o;
                }
            }
            if(talent==null){
                return null;
            }
            RtfWriter2.getInstance(doc, out);
            doc.open();
            CreateDocHeader(doc);
            Table titleTable = new Table(1, 1);
            titleTable.setAlignment(Element.ALIGN_CENTER);
            titleTable.setWidth(110);
            titleTable.setBorderWidth(0);
            Cell cell = new Cell(new Phrase(talent.getName() + "的简历", ChineseFont(true, false, 23)));
            cell.setBorderWidth(0);
            titleTable.addCell(cell);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            doc.add(titleTable);
            // 设置简历个人信息
            CreateBasicInfoTable(doc, talentMap);
            // 设置简历求职意向
            CreateExpectInfoTable(doc, talentMap);
            // 设置工作经验
            CreateWorkExpInfoTable(doc, talentMap);
            // 设置教育经历
            CreateEduExpInfoTable(doc, talentMap);
            // 设置项目经验
            CreateProjectExpInfoTable(doc, talentMap);
            //培训经历
            CreateTrainExpInfoTable(doc, talentMap);
            //语言能力
            CreateLanguageAbilityInfoTable(doc,talentMap);
            //证书信息
            CreateCertificationInfoTable(doc,talentMap);
            // 设置自我评价
            CreateSelfEvaluationExpInfoTable(doc, talentMap);
            // 设置扩展评价
            CreateExtraMsgTable(doc, talentMap);
            // 关闭
            doc.close();
            return out.toByteArray();
        } catch (BadElementException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (DocumentException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally{
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 基本信息创建
     * 
     * @param refWriter
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateBasicInfoTable(Document doc, Map<String, Object> talentMap) {
        Talent talent = null;
        if (MapUtils.isNotEmpty(talentMap)) {
            Object o = talentMap.get("talent");
            if (o instanceof Talent) {
                talent = (Talent) o;
            }
        }
        if (talent == null) {
            return;
        }
        try {
            Table baseInfoTable = new Table(2, 3);
            baseInfoTable.setBorderWidth(0);
            baseInfoTable.setAlignment(1);
            baseInfoTable.setWidth(110);

            Cell cell0 = new Cell(new Chunk("基本信息", ChineseFont(true, true, 18)));// 基本信息
            cell0.setColspan(2);
            // 设置下划线
            cell0.add(rtfShape());

            Cell cell1 = new Cell(new Phrase("\t姓  名： ", twoTitleFont()));
            cell1.add(new Phrase(talent.getName() , ChineseFont(false, false, 12)));
            cell1.setBorderWidth(0);

            String gender = "";
            if (talent.getGender() != null) {
                gender = talent.getGender() == 0 ? "女" : "男";
            }

            Cell cell2 = new Cell(new Phrase("性  别： ", twoTitleFont()));
            cell2.add(new Phrase(gender , ChineseFont(false, false, 12)));

            Cell cell3 = new Cell(new Phrase("\t电  话： ", twoTitleFont()));
            cell3.setRowspan(2);
            cell3.add(new Phrase(talent.getPhone(), ChineseFont(false, false, 12)));

            Cell cell4 = new Cell(new Phrase("邮  箱： ", twoTitleFont()));
            cell4.setRowspan(2);
            cell4.add(new Phrase(talent.getEmail() , ChineseFont(false, false, 12)));

            Cell cell5 = new Cell(new Phrase("\t地  址： ", twoTitleFont()));
            cell5.add(new Phrase(talent.getCity() , ChineseFont(false, false, 12)));

            Cell cell6 = new Cell(new Phrase("生  日： ", twoTitleFont()));
            cell6.add(new Phrase(talent.getBirthday() , ChineseFont(false, false, 12)));
            // 单元格水平对齐方式 单元格垂直对齐方式
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell4, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell5, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell6, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            // 添加进入表格
            baseInfoTable.addCell(cell0);
            baseInfoTable.addCell(cell1);
            baseInfoTable.addCell(cell2);
            baseInfoTable.addCell(cell3);
            baseInfoTable.addCell(cell4);
            baseInfoTable.addCell(cell5);
            baseInfoTable.addCell(cell6);
            

            Cell cellBlank = new Cell(new Phrase("", twoTitleFont()));
            cellBlank.setRowspan(2);
            cellBlank.setColspan(2);
            cellBlank.setBorderWidth(0);
            baseInfoTable.addCell(cellBlank);
            doc.add(baseInfoTable);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 求职意向创建
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateExpectInfoTable(Document doc, Map<String, Object> talentMap) {
        Talent talent = null;
        if (MapUtils.isNotEmpty(talentMap)) {
            Object o = talentMap.get("talent");
            if (o instanceof Talent) {
                talent = (Talent) o;
            }
        }

        if (talent == null) {
            return;
        }
        try {
            Table baseInfoTable = new Table(1, 3);
            baseInfoTable.setAlignment(1);
            baseInfoTable.setWidth(110);

            Cell cell0 = new Cell(new Chunk("求职意向", ChineseFont(true, true, 18)));// 基本信息
            cell0.add(rtfShape());

            Cell cell1 = new Cell(new Phrase("+\t期望行业： ", twoTitleFont()));
            cell1.add(new Phrase(talentMap.get("expectIndustry") + "\n", ChineseFont(false, false, 12)));

            Cell cell2 = new Cell(new Phrase("\t期望职位： ", twoTitleFont()));
            cell2.add(new Phrase(talent.getExpectPositionTitles() + "\n", ChineseFont(false, false, 12)));

            String anualSalary = talent.getAnualSalary() != null && talent.getAnualSalary() != 0 ? talent
                    .getAnualSalary() / 10000 + "月" : 0 + "月";
            String currentSalary = talent.getCurrentSalary() != null && talent.getCurrentSalary() != 0 ? talent
                    .getCurrentSalary() + "月" : 0 + "月";
            String currentSalaryMonths = talent.getCurrentSalaryMonths() != null ? talent.getCurrentSalaryMonths()
                    + "个月" : 0 + "个月";
            String other = talent.getOtherWelfare() != null ? talent.getOtherWelfare() : "";

            Cell cell3 = new Cell(new Phrase("\t目前年薪： ", twoTitleFont()));
            cell3.add(new Phrase(
                    anualSalary + " " + currentSalary + "/月 * " + currentSalaryMonths + " " + other + "\n",
                    ChineseFont(false, false, 12)));

            String ExpectAnnualSalaryDesc = StringTools.isBlank(talent.getExpectAnnualSalaryDesc() + "") ? "" : talent
                    .getExpectAnnualSalaryDesc() + "";
            Cell cell4 = new Cell(new Phrase("\t期望年薪： ", twoTitleFont()));
            cell4.add(new Phrase(ExpectAnnualSalaryDesc + "\n", ChineseFont(false, false, 12)));

            String expectCity = StringTools.isBlank(talentMap.get("expectCity")) ? "\n" : talentMap.get("expectCity")+"\n";
            Cell cell5 = new Cell(new Phrase("\t期望城市： ", twoTitleFont()));
            cell5.add(new Phrase(expectCity, ChineseFont(false, false, 12)));
            // 单元格水平对齐方式 单元格垂直对齐方式
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell4, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell5, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            // 添加进入表格
            baseInfoTable.addCell(cell0);
            baseInfoTable.addCell(cell1);
            baseInfoTable.addCell(cell2);
            baseInfoTable.addCell(cell3);
            baseInfoTable.addCell(cell4);
            baseInfoTable.addCell(cell5);
            doc.add(baseInfoTable);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    
    
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 工作经历创建
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateWorkExpInfoTable(Document doc, Map<String, Object> talentMap) {
        TalentResumeDetailExp exp = new TalentResumeDetailExp();
        if (MapUtils.isNotEmpty(talentMap)) {
            Object o = talentMap.get("exp");
            if (o instanceof TalentResumeDetailExp) {
                exp = (TalentResumeDetailExp) o;
            }
        }
        List<TalentResumeWorkExp> workExpList = exp.getWorkExps();
        if (CollectionUtils.isEmpty(workExpList)) {
            return;
        }
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("工作经历", ChineseFont(true, true, 18)));// 基本信息
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            for (int i = 0; i < workExpList.size(); i++) {
                TalentResumeWorkExp workExp = workExpList.get(i);
                Table baseInfoTable = new Table(3, 2);
                // 设置表格右对齐，其中1为居中对齐，2为右对齐，3为左对齐
                baseInfoTable.setAlignment(1);
                baseInfoTable.setWidth(110);
                int[] widths = { 30, 50, 30 };
                baseInfoTable.setWidths(widths);
                String startYearAndMonth = workExp.getStartYear() + "";
                if (StringTools.isNotBlank(startYearAndMonth)) {
                    String endMonth = StringTools.isNotBlank(workExp.getStartMonth() + "") ? "."
                            + workExp.getStartMonth() : "";
                    startYearAndMonth = startYearAndMonth + endMonth;
                }
                String endYearAndMonth = workExp.getEndYear() + "";
                if (StringTools.isNotBlank(endYearAndMonth)) {
                    if ("9999".equals(endYearAndMonth)) {
                        endYearAndMonth = "至今";
                    } else {
                        String endMonth = StringTools.isNotBlank(workExp.getEndMonth() + "") ? "."
                                + workExp.getEndMonth() : "";
                        endYearAndMonth = endYearAndMonth + endMonth;
                    }
                }
                String expTime = startYearAndMonth + " - " + endYearAndMonth;
                String companyName = workExp.getCompanyName();
                String dept = workExp.getDepartment();
                Cell cell1 = new Cell(new Phrase("\t" + expTime + "\n", ChineseFont(false, false, 12)));
                Cell cell2 = new Cell(new Phrase(companyName + "\n", ChineseFont(false, false, 12)));
                Cell cell3 = new Cell(new Phrase(dept + "\n", ChineseFont(false, false, 12)));
                Cell cell4 = new Cell(new Phrase("\t职位名称： " + workExp.getPosition()+"\n", ChineseFont(true, false, 12)));
                cell4.setColspan(3);
                Cell cell5 = new Cell(new Paragraph("\t工作描述： ", twoTitleFont()));
                cell5.setColspan(3);
                // 单元格水平对齐方式 单元格垂直对齐方式
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell4, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell5, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                baseInfoTable.addCell(cell1);
                baseInfoTable.addCell(cell2);
                baseInfoTable.addCell(cell3);
                baseInfoTable.addCell(cell4);
                baseInfoTable.addCell(cell5);
                doc.add(baseInfoTable);
                Table descriptionTable = new Table(2, 1);
                descriptionTable.setAlignment(1);
                descriptionTable.setWidth(110);
                descriptionTable.setLocked(true);
                int[] width1 = { 7, 103 };
                descriptionTable.setWidths(width1);
                Cell emptyCell = new Cell();
                Cell cell6 = new Cell(new Phrase(replace(workExp.getDescription()), ChineseFont(false, false, 12)));
                setHorizontalAndVerticalAlignment(cell6, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(emptyCell, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                descriptionTable.addCell(emptyCell);
                descriptionTable.addCell(cell6);
                doc.add(descriptionTable);
                createDashed(doc, 190, 0);;;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 教育经历创建
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateEduExpInfoTable(Document doc, Map<String, Object> talentMap) {
        TalentResumeDetailExp exp = new TalentResumeDetailExp();
        if (MapUtils.isNotEmpty(talentMap)) {
            Object o = talentMap.get("exp");
            if (o instanceof TalentResumeDetailExp) {
                exp = (TalentResumeDetailExp) o;
            }
        }
        List<TalentResumeEduExp> eduExpList = exp.getEduExps();
        if (CollectionUtils.isEmpty(eduExpList)) {
            return;
        }
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("教育经历", ChineseFont(true, true, 18)));
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            for (int i = 0; i < eduExpList.size(); i++) {
                TalentResumeEduExp eduExp = eduExpList.get(i);
                Table baseInfoTable = new Table(4, 1);
                baseInfoTable.setAlignment(1);
                baseInfoTable.setWidth(110);
                int[] widths = { 30, 40, 35, 15 };
                baseInfoTable.setWidths(widths);
                String startYearAndMonth = eduExp.getStartYear() + "";
                if (StringTools.isNotBlank(startYearAndMonth)) {
                    String endMonth = StringTools.isNotBlank(eduExp.getStartMonth() + "") ? "."
                            + eduExp.getStartMonth() : "";
                    startYearAndMonth = startYearAndMonth + endMonth;
                }
                String endYearAndMonth = eduExp.getEndYear() + "";
                if (StringTools.isNotBlank(endYearAndMonth)) {
                    if ("9999".equals(endYearAndMonth)) {
                        endYearAndMonth = "至今";
                    } else {
                        String endMonth = StringTools.isNotBlank(eduExp.getEndMonth() + "") ? "."
                                + eduExp.getEndMonth() : "";
                        endYearAndMonth = endYearAndMonth + endMonth;
                    }
                }
                String expTime = startYearAndMonth + " - " + endYearAndMonth;
                String school = eduExp.getSchool();
                String profession = eduExp.getProfession();
                String degree = ConverDegree(eduExp.getDegreeId());
                Cell cell1 = new Cell(new Phrase("\t" + expTime, ChineseFont(false, false, 12)));
                Cell cell2 = new Cell(new Phrase(school, ChineseFont(false, false, 12)));
                Cell cell3 = new Cell(new Phrase(profession, ChineseFont(false, false, 12)));
                Cell cell4 = new Cell(new Phrase(degree, ChineseFont(false, false, 12)));
                createDashed(doc, 190, 0);
                // 单元格水平对齐方式 单元格垂直对齐方式
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell4, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                baseInfoTable.addCell(cell1);
                baseInfoTable.addCell(cell2);
                baseInfoTable.addCell(cell3);
                baseInfoTable.addCell(cell4);
                doc.add(baseInfoTable);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 项目经验创建
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateProjectExpInfoTable(Document doc, Map<String, Object> talentMap) {
        TalentResumeDetailExp exp = new TalentResumeDetailExp();
        if (MapUtils.isNotEmpty(talentMap)) {
            Object o = talentMap.get("exp");
            if (o instanceof TalentResumeDetailExp) {
                exp = (TalentResumeDetailExp) o;
            }
        }
        List<TalentResumeProjectExp> projectExpList = exp.getProjectExps();
        if (CollectionUtils.isEmpty(projectExpList)) {
            return;
        }
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("项目经验", ChineseFont(true, true, 18)));// 基本信息
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            for (int i = 0; i < projectExpList.size(); i++) {
                TalentResumeProjectExp projectExp = projectExpList.get(i);
                Table baseInfoTable = new Table(2, 2);
                baseInfoTable.setAlignment(1);
                baseInfoTable.setWidth(110);
                String startYearAndMonth = projectExp.getStartYear() + "";
                if (StringTools.isNotBlank(startYearAndMonth)) {
                    String endMonth = StringTools.isNotBlank(projectExp.getStartMonth() + "") ? "."
                            + projectExp.getStartMonth() : "";
                    startYearAndMonth = startYearAndMonth + endMonth;
                }
                String endYearAndMonth = projectExp.getEndYear() + "";
                if (StringTools.isNotBlank(endYearAndMonth)) {
                    if ("9999".equals(endYearAndMonth)) {
                        endYearAndMonth = "至今";
                    } else {
                        String endMonth = StringTools.isNotBlank(projectExp.getEndMonth() + "") ? "."
                                + projectExp.getEndMonth() : "";
                        endYearAndMonth = endYearAndMonth + endMonth;
                    }
                }
                String expTime = startYearAndMonth + " - " + endYearAndMonth;
                String projectName = StringTools.isBlank(projectExp.getProjectName()) ? "" : projectExp
                        .getProjectName();
                String description = replace(projectExp.getDescription());
                Cell cell1 = new Cell(new Phrase("\t" + expTime, ChineseFont(false, false, 12)));
                Cell cell2 = new Cell(new Phrase(projectName, ChineseFont(false, false, 12)));
                Cell cell3 = new Cell(new Phrase("\t项目描述：", twoTitleFont()));
                cell3.setColspan(2);
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                baseInfoTable.addCell(cell1);
                baseInfoTable.addCell(cell2);
                baseInfoTable.addCell(cell3);
                doc.add(baseInfoTable);

                // 对齐上方表格
                Table descriptionTable = new Table(2, 1);
                descriptionTable.setAlignment(1);
                descriptionTable.setWidth(110);
                descriptionTable.setLocked(true);
                int[] width1 = { 7, 103 };
                descriptionTable.setWidths(width1);
                Cell emptyCell = new Cell();
                Cell cell4 = new Cell(new Phrase(description, ChineseFont(false, false, 12)));
                setHorizontalAndVerticalAlignment(emptyCell, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell4, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                descriptionTable.addCell(emptyCell);
                descriptionTable.addCell(cell4);
                doc.add(descriptionTable);
                createDashed(doc, 190, 0);;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    
    
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 培训经历创建
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateTrainExpInfoTable(Document doc, Map<String, Object> talentMap) {
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("培训经历", ChineseFont(true, true, 18)));
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            TalentResumeDetailExp exp = new TalentResumeDetailExp();
            if (MapUtils.isNotEmpty(talentMap)) {
                Object o = talentMap.get("exp");
                if (o instanceof TalentResumeDetailExp) {
                    exp = (TalentResumeDetailExp) o;
                }
            }
            List<TalentResumeTrainExp> trainExpList = exp.getTrainExps();
            if (CollectionUtils.isEmpty(trainExpList)) {
                return;
            }

            for (int i = 0; i < trainExpList.size(); i++) {
                TalentResumeTrainExp trainExp = trainExpList.get(i);
                Table trainfoTable = new Table(2, 4);
                trainfoTable.setAlignment(1);// 向右对齐
                trainfoTable.setWidth(97);
                int[] widths = { 30, 67 };
                trainfoTable.setWidths(widths);
                String startYearAndMonth = trainExp.getStartYear() + "";
                if (StringTools.isNotBlank(startYearAndMonth)) {
                    String endMonth = StringTools.isNotBlank(trainExp.getStartMonth() + "") ? "."
                            + trainExp.getStartMonth() : "";
                    startYearAndMonth = startYearAndMonth + endMonth;
                }
                String endYearAndMonth = trainExp.getEndYear() + "";
                if (StringTools.isNotBlank(endYearAndMonth)) {
                    if ("9999".equals(endYearAndMonth)) {
                        endYearAndMonth = "至今";
                    } else {
                        String endMonth = StringTools.isNotBlank(trainExp.getEndMonth() + "") ? "."
                                + trainExp.getEndMonth() : "";
                        endYearAndMonth = endYearAndMonth + endMonth;
                    }
                }
                String expTime = startYearAndMonth + " - " + endYearAndMonth;
                String courseName = trainExp.getCourse();// 课程名称
                String agencyName = trainExp.getAgencyName();// 培训机构名称
                Cell cell1 = new Cell(new Phrase(expTime, ChineseFont(false, false, 12)));
                cell1.setRowspan(2);
                Cell cell2 = new Cell(new Phrase(courseName, ChineseFont(false, false, 12)));
                cell2.setRowspan(2);
                Cell cell3 = new Cell(new Phrase("培训机构：" + agencyName, ChineseFont(false, false, 12)));
                cell3.setColspan(2);
                cell3.setRowspan(2);
                // 单元格水平对齐方式 单元格垂直对齐方式
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                trainfoTable.addCell(cell1);
                trainfoTable.addCell(cell2);
                trainfoTable.addCell(cell3);
                doc.add(trainfoTable);
                createDashed(doc, 190, 0);
                ;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }  
    
    
    
    
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 语言能力
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateLanguageAbilityInfoTable(Document doc, Map<String, Object> talentMap) {
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("语言能力", ChineseFont(true, true, 18)));
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            TalentResumeDetailExp exp = new TalentResumeDetailExp();
            if (MapUtils.isNotEmpty(talentMap)) {
                Object o = talentMap.get("exp");
                if (o instanceof TalentResumeDetailExp) {
                    exp = (TalentResumeDetailExp) o;
                }
            }
            List<TalentResumeLanguageAbility> languageExpList = exp.getLanguageAbilitys();
            if (CollectionUtils.isEmpty(languageExpList)) {
                return;
            }

            for (int i = 0; i < languageExpList.size(); i++) {
                TalentResumeLanguageAbility languageExp = languageExpList.get(i);
                if (languageExp == null) {
                    return;
                }
                Table trainfoTable = new Table(4, 2);
                trainfoTable.setAlignment(1);// 向右对齐
                trainfoTable.setWidth(97);
                int[] widths = { 25, 25, 25, 12 };
                trainfoTable.setWidths(widths);
                String languageName = languageExp.getLanguageName();
                String literacy = "读写能力"+conver(languageExp.getLiteracy());// 读写能力
                String verbal = "听说能力"+conver(languageExp.getVerbal());// 
                String leave = languageExp.getLanguageLevel();
                Cell cell1 = new Cell(new Phrase(languageName, ChineseFont(false, false, 12)));
                cell1.setRowspan(2);
                Cell cell2 = new Cell(new Phrase(literacy, ChineseFont(false, false, 12)));
                cell2.setRowspan(2);
                Cell cell3 = new Cell(new Phrase(verbal, ChineseFont(false, false, 12)));
                cell3.setRowspan(2);
                Cell cell4 = new Cell(new Phrase(leave, ChineseFont(false, false, 12)));
                cell4.setRowspan(2);
                // 单元格水平对齐方式 单元格垂直对齐方式
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell4, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                trainfoTable.addCell(cell1);
                trainfoTable.addCell(cell2);
                trainfoTable.addCell(cell3);
                trainfoTable.addCell(cell4);
                doc.add(trainfoTable);
                createDashed(doc, 190, 0);
                ;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    } 
    
    
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 证书信息
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateCertificationInfoTable(Document doc, Map<String, Object> talentMap) {
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("证书信息", ChineseFont(true, true, 18)));
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            TalentResumeDetailExp exp = new TalentResumeDetailExp();
            List<TalentResumeCertification> certificationExpList = exp.getCertifications();
            if (CollectionUtils.isEmpty(certificationExpList)) {
                return;
            }
            for (int i = 0; i < certificationExpList.size(); i++) {
                TalentResumeCertification certificationInfo = certificationExpList.get(i);
                Table trainfoTable = new Table(2, 2);
                trainfoTable.setAlignment(1);// 向右对齐
                trainfoTable.setWidth(97);
                int[] widths = { 30, 67 };
                trainfoTable.setWidths(widths);
                String issuedTime = "";
                String year = certificationInfo.getIssuedYear() + "";
                String month = certificationInfo.getIssuedMonth() + "";
                if (StringTools.isNotBlank(year)) {
                    issuedTime = StringTools.isNotBlank(month) ? year + "." + month : year;
                }
                String cerName = certificationInfo.getCerName();
                String description = certificationInfo.getDescription();
                Cell cell1 = new Cell(new Phrase(issuedTime, ChineseFont(false, false, 12)));
                Cell cell2 = new Cell(new Phrase(cerName, ChineseFont(false, false, 12)));
                Cell cell3 = new Cell(new Phrase("证书描述：" + description, ChineseFont(false, false, 12)));
                cell3.setColspan(0);
                // 单元格水平对齐方式 单元格垂直对齐方式
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                trainfoTable.addCell(cell1);
                trainfoTable.addCell(cell2);
                trainfoTable.addCell(cell3);
                doc.add(trainfoTable);
                createDashed(doc, 190, 0);
                ;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
 
    
    
    
    
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param literacy
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String conver(Integer literacy) {
        String res = "";
        // 1 一般 良好 熟练 精通
        if (literacy == null || literacy <= 0) {
            return res;
        }
        switch (literacy) {
            case 1:
                res = "一般";
                break;
            case 2:
                res = "良好";
                break;
            case 3:
                res = "熟练";
                break;
            case 4:
                res = "精通";
                break;

            default:
                break;
        }
        return res;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉候选人自评
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateSelfEvaluationExpInfoTable(Document doc, Map<String, Object> talentMap) {
        Talent talent = null;
        if (MapUtils.isNotEmpty(talentMap)) {
            Object o = talentMap.get("talent");
            if (o instanceof Talent) {
                talent = (Talent) o;
            }
        }
        if (talent == null) {
            return;
        }
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("我的优势", ChineseFont(true, true, 18)));// 基本信息
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            Table selfTable = new Table(2, 3);
            selfTable.setWidth(110);
            selfTable.setLocked(true);
            int[] widths = { 20, 90 };
            selfTable.setWidths(widths);
            selfTable.setAlignment(1);
            selfTable.setBorderWidth(1);
            Cell cell1 = null;
            Cell cell2 = null;
            Cell cell7=null;
            Cell cell8=null;
            if ("2".equals(talentMap.get("techOrDes"))) {
                cell1 = new Cell(new Phrase("\t特长标签：", twoTitleFont()));
                cell7= new Cell(new Phrase(talent.getExpertTagContent(), ChineseFont(false, false, 12)));
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell7, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                selfTable.addCell(cell1);
                selfTable.addCell(cell7);
            } else {
                cell1 = new Cell(new Phrase("\t精通技能：", twoTitleFont()));
                cell7 =new Cell(new Phrase(talent.getExpertTagContent() , ChineseFont(false, false, 12)));
                cell2 = new Cell(new Phrase("\t熟悉技能：", twoTitleFont()));
                cell8=new Cell(new Phrase(talent.getCompetentTagContent() , ChineseFont(false, false, 12)));
                setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell7, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell2, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                setHorizontalAndVerticalAlignment(cell8, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
                selfTable.addCell(cell1);
                selfTable.addCell(cell7);
                selfTable.addCell(cell2);
                selfTable.addCell(cell8);
            }
            Cell cell3 = new Cell(new Phrase("\t自我简介：", twoTitleFont()));
            cell3.setColspan(2);
            cell3.setWidth(1);
            setHorizontalAndVerticalAlignment(cell3, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            selfTable.addCell(cell3);
            doc.add(selfTable);
            
            // 对齐上方表格
            Table descriptionTable = new Table(2, 1);
            descriptionTable.setAlignment(1);
            descriptionTable.setWidth(110);
            descriptionTable.setLocked(true);
            int[] width1 = { 7, 103 };
            descriptionTable.setWidths(width1);
            Cell emptyCell = new Cell();
            Cell cell4 = new Cell(new Phrase(replace(talent.getSelfEvaluation()), ChineseFont(false, false, 12)));
            setHorizontalAndVerticalAlignment(emptyCell, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell4, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            descriptionTable.addCell(emptyCell);
            descriptionTable.addCell(cell4);
            doc.add(descriptionTable);
            createDashed(doc, 190, 0);;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉添加附件信息
     *
     * @param doc
     * @param talentMap
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void CreateExtraMsgTable(Document doc, Map<String, Object> talentMap) {
        Talent talent = null;
        if (MapUtils.isNotEmpty(talentMap)) {
            Object o = talentMap.get("talent");
            if (o instanceof Talent) {
                talent = (Talent) o;
            }
        }
        if (talent == null) {
            return;
        }
        try {
            Table expTable = new Table(1, 1);
            expTable.setBorderWidth(0);
            expTable.setAlignment(1);
            expTable.setWidth(110);
            Cell cell0 = new Cell(new Chunk("附加信息", ChineseFont(true, true, 18)));// 基本信息
            cell0.add(rtfShape());
            setHorizontalAndVerticalAlignment(cell0, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            expTable.addCell(cell0);
            doc.add(expTable);
            // 对齐上方表格
            Table descriptionTable = new Table(2, 1);
            descriptionTable.setAlignment(1);
            descriptionTable.setWidth(110);
            descriptionTable.setLocked(true);
            int[] width1 = { 7, 103 };
            descriptionTable.setWidths(width1);
            Cell emptyCell = new Cell();
            Cell cell1 = new Cell(new Phrase(replace(talent.getSelfEvaluation()), ChineseFont(false, false, 12)));
            setHorizontalAndVerticalAlignment(emptyCell, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            setHorizontalAndVerticalAlignment(cell1, Element.ALIGN_LEFT, Element.ALIGN_CENTER);
            descriptionTable.addCell(emptyCell);
            descriptionTable.addCell(cell1);
            doc.add(descriptionTable);
            createDashed(doc, 190, 0);;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * 
     * 功能描述: <br>
     * 〈转换学历〉
     *
     * @param degree
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String ConverDegree(Integer degree) {
        String degreeString = "";
        switch (degree) {
            case 1:
                degreeString = "大专"; // 大专及以上
                break;
            case 2:
                degreeString = "本科"; // 本科及以上
                break;
            case 3:
                degreeString = "硕士"; // 硕士及以上
                break;
            case 4:
                degreeString = "博士"; // 博士及以上
                break;
        }
        return degreeString;
    }

    public static String replace(String descripTion) {
        if (StringTools.isBlank(descripTion)) {
            return "";
        }
        return descripTion.replaceAll("<br />", "\n").replace("<br>", "\n").replaceAll("\r", "\n");
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉pdf文档中文字符设置宋体
     *
     * @param Bold
     * @param red
     * @param size
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Font ChineseFont(boolean Bold, boolean red, int size) {
        Font baseFont = null;
        // 创建基础字体
        String path = WordUtils.class.getClassLoader().getResource(fontPath).getPath();
        FontFactory.register(path);
        baseFont = FontFactory.getFont("宋体", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        if (Bold) {
            baseFont.setStyle(1);
        } else {
            baseFont.setStyle(0);
        }
        if (red) {
            baseFont.setColor(new Color(192, 0, 0));
        } else {
            baseFont.setColor(Color.BLACK);
        }
        if (size != 0) {
            baseFont.setSize(size);
            ;
        } else {
            baseFont.setSize(12);
        }
        return baseFont;
    }

    // 创建二级标题字体灰色
    public static Font twoTitleFont() {
        Font font = ChineseFont(false, false, 12);
        font.setColor(153, 153, 153);
        return font;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉创建文档Header
     *
     * @param doc
     * @param talent
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static Document CreateDocHeader(Document doc) {
        try {
            String imagePath = WordUtils.class.getClassLoader().getResource(IMG_PATH).getPath();
            Image headerImage = Image.getInstance(imagePath);
            headerImage.setAlignment(3);
            Table tables = new Table(1, 1);
            tables.setWidth(110);
            tables.setBorderWidth(0);
            tables.setBorder(5);
            tables.setAlignment(3);
            Cell cells = new Cell(headerImage);
            cells.addElement(new Paragraph("www.hunteron.com", ChineseFont(true, false, 11)));
            cells.setBorder(0);
            cells.setVerticalAlignment(3);
            cells.setHorizontalAlignment(3);
            tables.addCell(cells);
            RtfHeaderFooter header_image = new RtfHeaderFooter(tables);
            doc.setHeader(header_image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return doc;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉生成带下划线的字体
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Paragraph fontUnderLine(String str) {
        Font font3 = new Font(BaseFont.UNDERLINE_THICKNESS, 12, Font.UNDERLINE);
        Paragraph context = new Paragraph(str, font3);
        context.setAlignment(Element.ALIGN_LEFT);
        return context;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉使用Rtf绘制直线
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Paragraph rtfShape() {
        RtfShapePosition position = new RtfShapePosition(150, 0, 11500, 150);
        position.setXRelativePos(RtfShapePosition.POSITION_X_RELATIVE_MARGIN);
        position.setYRelativePos(RtfShapePosition.POSITION_Y_RELATIVE_PARAGRAPH);
        position.setFont(twoTitleFont());
        RtfShape shape = new RtfShape(RtfShape.SHAPE_LINE, position);
        RtfShapeProperty property = new RtfShapeProperty(RtfShapeProperty.PROPERTY_LINE_COLOR, new Color(153, 153, 153));
        shape.setProperty(property);
        Paragraph paragraph = new Paragraph();
        paragraph.add(shape);
        return paragraph;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉设置单元格位置
     *
     * @param cell
     * @param alignLeft
     * @param alignCenter
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void setHorizontalAndVerticalAlignment(Cell cell, int alignLeft, int alignCenter) {
        cell.setHorizontalAlignment(alignLeft);
        cell.setVerticalAlignment(alignCenter);
        cell.setBorderWidth(0);
    }
    
    /**
     * 在doc上画虚线
     *
     * @param doc
     * @param dashedLen 虚线数量
     * @param left 做偏移
     * @throws DocumentException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void createDashed(Document doc, int dashedLen, int left) throws DocumentException {
        Table table = new Table(1, 1);
        table.setBorderWidth(0);
        table.setWidth(110);
        Cell c = new Cell();
        int lastEnd = 0;
        lastEnd = left + 30;
        RtfShapePosition position;
        position = new RtfShapePosition(0, left, left + 30, 0);
        position.setXRelativePos(RtfShapePosition.POSITION_X_RELATIVE_MARGIN);
        position.setYRelativePos(RtfShapePosition.POSITION_Y_RELATIVE_PARAGRAPH);
        RtfShape shape = new RtfShape(RtfShape.SHAPE_LINE, position);
        RtfShapeProperty property = new RtfShapeProperty(RtfShapeProperty.PROPERTY_LINE_COLOR, Color.gray);
        shape.setProperty(property);
        c.add(shape);
        c.setBorderWidth(0);
        for (int i = 0; i <= dashedLen; i++) {
            left = lastEnd + 30;
            lastEnd = left + 30;
            position = new RtfShapePosition(0, left, lastEnd, 0);
            position.setXRelativePos(RtfShapePosition.POSITION_X_RELATIVE_MARGIN);
            position.setYRelativePos(RtfShapePosition.POSITION_Y_RELATIVE_PAGE);
            shape = new RtfShape(RtfShape.SHAPE_LINE, position);
            property = new RtfShapeProperty(RtfShapeProperty.PROPERTY_LINE_COLOR, Color.gray);
            shape.setProperty(property);
            c.add(shape);
        }
        table.addCell(c);
        table.setAlignment(Element.ALIGN_CENTER);
        doc.add(table);
    }
    
    
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉输入流转输出流
     *
     * @param inputstream
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("unused")
    private static ByteArrayOutputStream convertInputStream(InputStream inputstream) {
        ByteArrayOutputStream baos = null;
        byte[] array = new byte[1024];
        int len = 0;
        try {
            baos = new ByteArrayOutputStream();
            while ((len = inputstream.read(array)) != -1) {
                baos.write(array, 0, len);
            }
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputstream != null) {
                try {
                    inputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Object> map=new HashMap<String,Object>();
        downResume(map);
    }
}

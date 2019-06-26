package generator;

import com.henu.papercommon.utils.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ConvertGenerator {
    public static void main(String[] args) {
        FileOutputStream fop = null;
        File file;

        try {

            Class clazz = Class.forName("com.henu.paperadmin.domain.ArticleDO");//根据类名获得其对应的Class对象 写上你想要的类名就是了 注意是全名 如果有包的话要加上 比如java.Lang.String
            Class clazzB = Class.forName("com.henu.paperadmin.dto.ArticleDTO");
            file = new File("D:\\StudyTools\\IntelliJ IDEA 2019.1.1\\workspace\\Technique\\paper\\CodeGeneration\\convert\\" + firstUpper(clazz.getSimpleName()) + "Convert.java");
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }else {
                file.createNewFile();
            }
            String clazzSimpleName = clazz.getSimpleName();
            String clazzBSimpleName = clazzB.getSimpleName();
            String clazzBName = firstLower(clazzB.getSimpleName());
            String clazzName = firstLower(clazz.getSimpleName());


            Field[] fields = clazz.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
            Set<String> importSet = new HashSet<String>();

            StringBuffer content = new StringBuffer("");

            content.append("package demo.convert;\n");

            importSet.add(clazz.getName());
            importSet.add(clazzB.getName());
            importSet.add("java.util.List");
            importSet.add("java.util.ArrayList");
            for(Field f : fields) {
                importSet.add(f.getType().getName());
            }

            for (String s : importSet) {
                content.append("import " + s + ";\n");
            }

            content.append("/**\ncreate by tiger's tool\n*/\n");

            //classToClassB
            content.append("public class " + clazzSimpleName + "Convert {" + "\n");
            content.append("public static " + clazzSimpleName + " " + clazzBName + "To" + clazzSimpleName + "(" + clazzBSimpleName + " " + clazzBName + ")" +"{\n");
            content.append("if("+ clazzBName +" == null){\n");
            content.append("return null;\n");
            content.append("}\n");
            content.append(clazzSimpleName + " " + clazzName  + " = new " + clazzSimpleName + "();\n");
            for(Field f : fields) {
                content.append(clazzName + ".set" + firstUpper(f.getName()) + "(" + clazzBName+".get" + firstUpper(f.getName())  +"());\n");
            }
            content.append("return " + clazzName + ";\n");
            content.append("}\n");

            //classBToClass
            content.append("public static " + clazzBSimpleName + " " + clazzName + "To" + clazzBSimpleName + "(" + clazzSimpleName + " " + clazzName + ")" +"{\n");
            content.append("if("+ clazzName +" == null){\n");
            content.append("return null;\n");
            content.append("}\n");
            content.append(clazzBSimpleName + " " + clazzBName  + " = new " + clazzBSimpleName + "();\n");
            for(Field f : fields) {
                content.append(clazzBName + ".set" + firstUpper(f.getName()) + "(" + clazzName+".get" + firstUpper(f.getName())  +"());\n");
            }
            content.append("return " + clazzBName + ";\n");
            content.append("}\n");

            //classListToClassBList
            content.append("public static List<" + clazzBSimpleName + ">" + clazzName + "ListTo" + clazzBSimpleName + "List(" + "List<" + clazzSimpleName +"> " + clazzName  +"List){\n");
            content.append("if(" + clazzName + "List == null" +"){\n");
            content.append("return null;\n");
            content.append("}\n");
            content.append("List<" + clazzBSimpleName + ">" + clazzBName + "List = new ArrayList();\n");
            content.append("for(" + clazzSimpleName + " " + clazzName + " : " + clazzName + "List" + "){\n");
            content.append(clazzBName + "List.add(" + clazzName + "To" + clazzBSimpleName + "(" + clazzName +")" + ");\n");
            content.append("}\n");
            content.append("return " + clazzBName + "List;\n");
            content.append("}\n");


            //classBListToClassList
            content.append("public static List<" + clazzSimpleName + ">" + clazzBName + "ListTo" + clazzSimpleName + "List(" + "List<" + clazzBSimpleName +"> " + clazzBName  +"List){\n");
            content.append("if(" + clazzBName + "List == null" +"){\n");
            content.append("return null;\n");
            content.append("}\n");
            content.append("List<" + clazzSimpleName + ">" + clazzName + "List = new ArrayList();\n");
            content.append("for(" + clazzBSimpleName + " " + clazzBName + " : " + clazzBName + "List" + "){\n");
            content.append(clazzName + "List.add(" + clazzBName + "To" + clazzSimpleName + "(" + clazzBName +")" + ");\n");
            content.append("}\n");
            content.append("return " + clazzName + "List;\n");

            content.append("}\n");
            content.append("}\n");

            // get the content in bytes
            byte[] contentInBytes = content.toString().getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String firstUpper(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public static String firstLower(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }
}

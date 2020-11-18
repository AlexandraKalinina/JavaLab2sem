import com.google.auto.service.AutoService;
/*import freemarker.template.TemplateException;*/
import dto.HtmlInputDto;
import freemarker.template.TemplateException;
import utils.MessageCreate;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HtmlForm", "HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить элементы с аннотацией HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        Set<? extends Element> annotatedElementsInput = roundEnv.getElementsAnnotatedWith(HtmlInput.class);
        //просматриваем каждый класс, который был анотирован нашей аннотацией HtmlForm или HtmlInput
        for (Element element : annotatedElements) {
            // получаем путь с class-файлам, путь куда мы хотим закинуть html
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // создадим путь к html-файлу
            String ftlpath = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // User.class -> User.html
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            //создаем путь к ftl файлу
            ftlpath = ftlpath.substring(1) + element.getSimpleName().toString() + ".ftl";
            // формируем путь для записи данных
            Path out = Paths.get(path);
            Path outftl = Paths.get(ftlpath);
            Map<String, Object> root = new HashMap<>();
            try {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "PATH " + out.toString());
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "PATH " + outftl.toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                BufferedWriter writer1 = new BufferedWriter(new FileWriter(outftl.toFile()));
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                writer.write("<form action='" + annotation.action() +
                        "' method='" + annotation.method() + "'/>");
                writer.write('\n');
                List<HtmlInputDto> inputs = new LinkedList<>();
                root.put("method", annotation.method());
                root.put("action", annotation.action());
                for (Element el: annotatedElementsInput) {
                    HtmlInput annotation1 = el.getAnnotation(HtmlInput.class);
                    HtmlInputDto dto = new HtmlInputDto(annotation1.type(), annotation1.name(), annotation1.placeholder());
                    inputs.add(dto);
                    writer.write('\t');
                    writer.write("<input type='" + annotation1.type() + "' name='"
                            + annotation1.name() + "' placeholder='" + annotation1.placeholder() + "'>");
                    writer.write('\n');
                }
                root.put("inputs", inputs);
                writer.write("</form>");
                writer.close();
                MessageCreate messageCreate = new MessageCreate();
                messageCreate.createMessage("formInput.ftl", root, writer1);
            } catch (IOException | TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}

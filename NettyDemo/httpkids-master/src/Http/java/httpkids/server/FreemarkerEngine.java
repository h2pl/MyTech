package httpkids.server;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import io.netty.handler.codec.http.HttpResponseStatus;

public class FreemarkerEngine implements ITemplateEngine {

	private Configuration config;

	public FreemarkerEngine(String templateRoot) {
		this.config = new Configuration(Configuration.VERSION_2_3_28);
		this.config.setClassForTemplateLoading(FreemarkerEngine.class, templateRoot);
	}

	@Override
	public String render(String path, Map<String, Object> context) {
		try {
			var template = config.getTemplate(path, "utf-8");
			StringWriter writer = new StringWriter();
			template.process(context, writer);
			return writer.toString();
		} catch (IOException e) {
			throw new AbortException(HttpResponseStatus.INTERNAL_SERVER_ERROR);
		} catch (TemplateException e) {
			throw new AbortException(HttpResponseStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

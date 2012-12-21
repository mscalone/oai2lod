package at.ac.univie.mminf.oai2lod;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NamespaceServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OAI2LODServer server = OAI2LODServer
				.fromServletContext(getServletContext());
		response.setContentType("text/javascript");
		ServletOutputStream out = response.getOutputStream();

		Map prefixes = server.getModel().getNsPrefixMap();

		out.println("// Generated dynamically from the mapping file");
		out.println("var D2R_namespacePrefixes = {");
		Iterator it = prefixes.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			out.print("\t\"" + entry.getKey() + "\": \"" + entry.getValue()
					+ "\"");
			if (it.hasNext()) {
				out.print(",");
			}
			out.println();
		}
		out.println("};");
	}

	private static final long serialVersionUID = -1172261621574174019L;
}

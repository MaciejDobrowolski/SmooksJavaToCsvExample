package pl.maciejdobrowolski;

import org.milyn.Smooks;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.JavaSource;
import org.xml.sax.SAXException;
import pl.maciejdobrowolski.model.Customer;

import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author macko
 * @since 2014-05-13
 */
public class Main {

    protected String runSmooksTransform(Object inputJavaObject) throws IOException, SAXException {
        Smooks smooks = new Smooks("smooks-config.xml");

        try {
            ExecutionContext executionContext = smooks.createExecutionContext();
            StringWriter writer = new StringWriter();

            // Filter the message to the result writer, using the execution context...
            smooks.filterSource(executionContext, new JavaSource(inputJavaObject), new StreamResult(writer));

            return writer.toString();
        } finally {
            smooks.close();
        }
    }


    public static void main(String[] args) throws IOException, SAXException {
        Main exampleMain = new Main();

        Customer customer = new Customer();
        customer.setFirstName("Maciej");
        customer.setLastName("Dobrowolski");
        customer.setAge(23);

        String translateResults;

        translateResults = exampleMain.runSmooksTransform(customer);

        System.out.println(translateResults);

    }
}

/**
 *
 */
package example.service;

public class HelloWorldService implements HelloWorldInterface {

    /* (非 Javadoc)
     * @see example.service.HelloWorldInterface#get(java.lang.String)
     */
    @Override
    public HelloResponse get(String message) {

        HelloResponse result = new HelloResponse("accept message from client", message);

        return result;
    }

}

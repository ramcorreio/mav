package com.stefanini.mav.startup;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

import org.apache.catalina.startup.Bootstrap;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MessageServerBootstrap {
	
	private static final Log log = LogFactory.getLog(Bootstrap.class);

	private static MessageServerBootstrap daemon = null;

	protected ClassLoader localClassLoader = null;

	private Object messageDaemon = null;

	public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		localClassLoader = getClass().getClassLoader();

		Class<?> startupClass = getClass().getClassLoader().loadClass("com.stefanini.mav.startup.MessageServer");
		Object startupInstance = startupClass.newInstance();
		Thread.currentThread().setContextClassLoader(localClassLoader);
		
		String methodName = "init";
		Method method = startupInstance.getClass().getMethod(methodName);
		method.invoke(startupInstance);

		/*String methodName = "setParentClassLoader";
		Class<?> paramTypes[] = new Class[1];
		paramTypes[0] = Class.forName("java.lang.ClassLoader");
		Object paramValues[] = new Object[1];
		paramValues[0] = localClassLoader;
		Method method = startupInstance.getClass().getMethod(methodName, paramTypes);
		method.invoke(startupInstance, paramValues);*/

		messageDaemon = startupInstance;
	}

	public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		
		
		/*return 
		if (daemon == null) {

			MessageServerBootstrap bootstrap = new MessageServerBootstrap();
			bootstrap.init();

			daemon = bootstrap;

		} else {

			Thread.currentThread().setContextClassLoader(daemon.localClassLoader);
		}

		try {
			String command = "start";
			if (args.length > 0) {
				command = args[args.length - 1];
			}

			if (command.equals("start")) {
				daemon.start();
			} else if (command.equals("stop")) {
				daemon.stop();;
			} else {
				log.warn("MessageServerBootstrap: command \"" + command + "\" does not exist.");
			}
		} catch (Throwable t) {
	
			if (t instanceof InvocationTargetException && t.getCause() != null) {
				t = t.getCause();
			}
			handleThrowable(t);
			t.printStackTrace();
			System.exit(1);
		}

		// server.logger.info("server already listenig at port " + PORT);
*/	}
	
	/**
    * Stop Daemon.
    */
   public void stop() throws Exception {

       Method method = messageDaemon.getClass().getMethod("stop");
       method.invoke(messageDaemon, (Object [] ) null);
   }
   
	/**
    * Start Daemon.
    */
   public void start() throws Exception {

       Method method = messageDaemon.getClass().getMethod("start");
       method.invoke(messageDaemon, (Object [] ) null);
   }
   
   private static void handleThrowable(Throwable t) {
       if (t instanceof ThreadDeath) {
           throw (ThreadDeath) t;
       }
       if (t instanceof VirtualMachineError) {
           throw (VirtualMachineError) t;
       }
       // All other instances of Throwable will be silently swallowed
   }
}

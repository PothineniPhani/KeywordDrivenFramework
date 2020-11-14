package test;

import org.testng.annotations.Test;

import engine.Engine;

public class EngineTest {
	
	public Engine engine;
	
	@Test
   public void LoginTest()
   {
		engine=new Engine();
		engine.startExecution("login");
   }
}

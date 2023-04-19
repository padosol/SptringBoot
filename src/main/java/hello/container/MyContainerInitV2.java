package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@HandlesTypes(AppInit.class)  // 이걸 선언해주면 onStartup의 Set<Class<?>> 에 해당 클래스가 딸려온다.
public class MyContainerInitV2 implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + c);
        System.out.println("MyContainerInitV2 ctx = " + ctx);

        for (Class<?> appIniteClass : c) {
            try {
                // new AppInitV1Servlet()과 같은 코드
                AppInit appinit = (AppInit) appIniteClass.getDeclaredConstructor().newInstance();
                appinit.onStartup(ctx);
            } catch (Exception e) {
            }
        }
    }
}

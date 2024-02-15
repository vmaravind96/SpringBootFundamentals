package ttl.larku.app.tricky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author whynot
 */
interface Trick {
    public void doTrick();
}

@Component
//@Primary
//@Profile("dev")
    @Qualifier("easy")
class Trick1 implements Trick
{
    @Override
    public void doTrick() {
        System.out.println("Handstand");
    }
}

@Component
@Qualifier("medium")
//@Profile("prod")
class Trick2 implements Trick
{
    @Override
    public void doTrick() {
        System.out.println("Somersault");
    }
}

@Component
@Qualifier("medium")
//@Profile("prod")
class Trick3 implements Trick
{
    @Override
    public void doTrick() {
        System.out.println("Somersault");
    }
}

@Component
class Circus
{
//    @Autowired
    private Trick trick;

    @Autowired
    @Qualifier("easy")
    private List<Trick> easyTricks;

    @Autowired
    @Qualifier("medium")
    private List<Trick> mediumTricks;

    public void startShow() {
//        trick.doTrick();
        easyTricks.forEach(Trick::doTrick);
        mediumTricks.forEach(Trick::doTrick);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.scan("ttl.larku.app.tricky");
        context.refresh();

        Circus circus = context.getBean("circus", Circus.class);

        circus.startShow();
    }
}

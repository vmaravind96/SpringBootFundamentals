package ttl.larku.app.tricky;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

interface Trick {
    public void doTrick();
}

@Component("trick1")
//@Primary
//@Profile("dev") // This will go into context only if the profile is set.
@Qualifier("easy")
class Trick1 implements Trick {

    @Override
    public void doTrick() {
        System.out.println("Trick 1");
    }
}

@Component
//@Qualifier("trick2") // Qualifier is a meta-data. This can be in name of the bean itself
//@Profile("prod")
@Qualifier("medium")
class Trick2 implements Trick {

    @Override
    public void doTrick() {
        System.out.println("Trick 2");
    }
}

@Component
//@Qualifier("trick2") // Qualifier is a meta-data. This can be in name of the bean itself
//@Profile("prod")
@Qualifier("medium")
class Trick3 implements Trick {

    @Override
    public void doTrick() {
        System.out.println("Trick 3");
    }
}


@Component("circus") // Name of the bean automatically becomes the qualifier
class Circus {

    //    private final Trick myTrick;
    //    private final Trick backupTrick;

    private List<Trick> easyTricks;

    private List<Trick> mediumTricks;

    /*
    Circus(@Qualifier("trick2") Trick trick2, @Qualifier("trick1") Trick trick1) {
        this.myTrick = trick2;
        this.backupTrick = trick1;
    }
     */

    /*
    Circus(Trick trick2, Trick trick1) {
        this.myTrick = trick2;
        this.backupTrick = trick1;
    }
     */

    Circus(@Qualifier("easy") List<Trick> easyTricks, @Qualifier("medium") List<Trick> mediumTricks){
        this.easyTricks = easyTricks;
        this.mediumTricks = mediumTricks;

    }

     public void startShow(){
         //        myTrick.doTrick();
         //        backupTrick.doTrick();
         System.out.println("Starter pack");
         easyTricks.forEach(Trick::doTrick);
         System.out.println("Get the audience in");
         mediumTricks.forEach(Trick::doTrick);
     }

     public static void main(String[] args){
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

         context.getEnvironment().setActiveProfiles("dev"); // Only trick1 does into context

         context.scan("ttl.larku.app.tricky");
         context.refresh();

         Circus circus = context.getBean("circus", Circus.class);
         circus.startShow();
     }
}
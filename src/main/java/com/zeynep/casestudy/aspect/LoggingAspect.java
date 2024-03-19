package com.zeynep.casestudy.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect//we have declared that this class is an aspect
@Component // IoC container icinde initialize olması icin , bu classı bean olarak initiliaz eden bir anatasyon
public class LoggingAspect {

    //bir metodu advice yapmak icin anatasyonlar var: before after
    //execution(public String get*()) --> wildcard bu metot herhangi bir parametre almıyor
    //* -> wildcard (joker karakter)
    //public i kaldırabilirsin  de

    /*
    @Before("execution(public * com.zeynep.casestudy.controller.AuthController.sign*(*))")
    public void loggingAdvice(){
        System.out.println("onceee calisiyooorr");
    }*/



    //execution(public * sign*(..)) --> boyle yapınca hem girisinde hem cıkısında calisti
    //sign*(..) arg olup olmadıgına bakılmaksızın tum sing lere calisir sıfır veya herhhangi bir sayıda arguman olabilir
    //sign*(*) arg alıyorsa calisir yine hem giriste hem cıkısta calisti hmm
    //cunku projende tum sign ile baslayan metotların oncesinde calisti :D
    //e benimde authservice de sign ile baslıyor tum metotlarım
    //yani hem controllerda hem de servis de aynı isimde baslıyor :D

    //yontemin kesmek istedigimiz nokta burası
    //burası bir point cut tır



    //bir metot ile sınırlamak vs

    //com.zeynep.casestudy.controller.*.sign*(*)
    //bu controller paketi icndeki tum sınıflarda sign ile baslayan tum metotların oncesinde calısacak


    /*com.zeynep.casestudy.controller.AuthController.signup(..) bu pattern ın
    * --> tumunun
    * execution --> execution ından once calis
    * simdi bu projemizi dinleyecek
    *
    *
    * com.zeynep.casestudy.controller.AuthController da bir signup metodu calisirsa onu yakala

    *
    *
    * JoinPoint o anki state i yakalar
    * cagrılacak metoto kim paam kim nerednen geliyoruz tum sureci burada tasıyor
    *
    *
    * */

    //yetki kontrolleri vs
    //spesific bir yeri dinlemek istiyorsak onu buraya yazabiliriz
    //tum sureci yakalayabiliriz
    //farklı bir threadde calistirabiliriz
    /*
    @Before("execution(com.zeynep.casestudy.controller.AuthorizationController.sayHello(..))")
    public void onceCalis(JoinPoint joinPoint){
        Object arg = joinPoint.getArgs()[0];//araya girdigi metottan gelen argumanlar
        System.out.println("onceCalis aspecti calisti: " + arg);
        System.out.println(joinPoint.getSignature());
    }

    //controller ın altındaki her  bir metot icin sanki kod yazmısız gibi execute ettiricez assagıdaki kodu
    @After("execution(com.zeynep.casestudy.controller.*(..))")
    public void sonraCalis(JoinPoint joinPoint){
        System.out.println("sonra calis aspecti");
        System.out.println(joinPoint.getSignature());
    } */
    //bir nevi araya giriyoruz

    //bir classdaki tum tum metotlar
    //bir paketteki tum metotlar

    //mesela save yapacaksın, save yapan kim ise git  onu
    // security contetxt ten o kullanıcıyı al
    //kullanıcı bilgilerine gore bir seyler yap, ekranan bas vs



    /*
    *   joinPoint.toString();
        Object[] args = joinPoint.getArgs();
        Object aThis = joinPoint.getThis();//joinPoint.getThis().getClass().getName()
        String kind = joinPoint.getKind();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();*/





    @Pointcut("within(com.zeynep.casestudy.controller.AuthController.*)")
    public void allServiceMethods2(){}


    /*moduler olan ayrı point cut lar yazalım
     * ve istedigin yere baglı olarak pointlerinde birlestirebilirsin
     * point cutlarını istedigin gibi cok esnek bir sekilde config edebilirsin*/


    //farklı noktalara farklı kesimlere uygulandıgında

    /*bu advice metotlarını uyg nın belirli noktalarında calisacak sekilde yapılandırmak icin
     * Pointcut ları yazdık
     *
     *
     * JoinPoint
    metotlar join pointtir(ortak noktalardır)
    * ortak noktalar yalnızca metotlardır
     *  spring otomatik olarak bu bilgiyi iletecek
     *
     * bir advice calistirildiginda spring ın gectigi argumandır
     * */



    @Pointcut("execution(public * com.zeynep.casestudy.service.*.*(*))")
    public void allServiceMethods(){}



    @Before("allMethods()")
    public void beforeAllMethods(){
        System.out.println("before aspect for all methods");
    }

    @After("allMethods()")
    public void afterAllMethods(){
    }


    //metod adı ne olursa olsun parametreler ne olursa olsun tuuum metotlar icin bir point cut olustur
    //buraya bir * koyunca calisti
    //@Pointcut("execution(* com.zeynep.casestudy.*.*(..))")
    //casestudy..* 2 noktaya kızıyor neden ??
    @Pointcut("execution(* com.zeynep.casestudy.*.*(..))")
    public void allMethods(){}

    //within(com.zeynep.casestudy.controller.AuthController.*) --> AuthController icindeki tum metotlara uygula
    //within(com.zeynep.casestudy.controller.*) controller paketindeki tum classlardaki tum metotlar icin uygula
    ////within(com.zeynep.casestudy.controller..*) alt paketler de olsun diyorsan

}

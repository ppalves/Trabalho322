package Equipiada.Components.Debugger.Debugger;

import Equipiada.Components.Debugger.IDebugger.IDebugger;
import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.Integer;
import java.io.FileWriter;


public class Debugger implements IDebugger {
    IDoctor doc;
    IPatient pat;

    public Debugger(IDoctor doc,IPatient pat){
        this.doc = doc;
        this.pat = pat;
    }

    public void connect(IDoctor doc,IPatient pat){
        this.doc = doc;
        this.pat = pat;
    }

    public Method[] debugPatient(){
        Class c = this.pat.getClass();
        Method[] m = c.getDeclaredMethods();
        System.out.println("Os métodos do paciente são");
        for (int i = 0; i < m.length; i++)
            System.out.println(m[i].toString());
        for (Field f : c.getDeclaredFields()){
            f.setAccessible(true);
            System.out.println("Atributo " + f.getName()+" do tipo:"+ f.getType());
            try{
                Object object = f.get(this.pat);
                if(object == null){
                    System.out.println("Valor do atributo : null");
                }
                if(object instanceof String){
                    System.out.println("Valor do atributo " + object);
                }
                if(f.getType() == Integer.class){
                    System.out.println("Valor do atributo " + object);

                }



            }
            catch(Exception e){
                e.printStackTrace();

            }
        }


        return m;
    }

    public Method[] debugDoctor(){
        Class c = this.doc.getClass();
        Method[] m = c.getDeclaredMethods();
        System.out.println("Os métodos métodods do doutor são");
        for (int i = 0; i < m.length; i++)
            //   String metodo = m[i].String();
            System.out.println(m[i].toString());

        return m;
    }

}
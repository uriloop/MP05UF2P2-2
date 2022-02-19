package ex4;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HashTableTest {
    HashTable ht = new HashTable();
    List<ClasseDeProba> llista;
    ClasseDeProba[] array;

    public HashTableTest() {
        llista= new ArrayList<>();
        array= new ClasseDeProba[3];
        for (int i = 0; i < 3; i++) {
            array[i]= new ClasseDeProba();
            llista.add(new ClasseDeProba());
        }
    }



    @Test
    void objectPuts() {

        ht.put("0", 0);       //   Inserir un element que no col·lisiona dins una taula vuida.
        ht.put("1", 1);            //  Inserir un element que no col·lisiona dins una taula no vuida.
        ht.put("11", 11);                    //  Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
        ht.put("22", 22);                        //    Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
        Assertions.assertEquals(ht.toString(), "\n bucket[0] = [0, 0] -> [11, 11] -> [22, 22]\n" +
                " bucket[1] = [1, 1]");                     // comprova que funcionen els puts
        Assertions.assertEquals(4, ht.count());     // comprova que funciona el count
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size

        // Resultat de la proba abans de fer els cambis:
        //          ht.count() = 0         -no augmenten els items al fer un put-

    }


    @Test
    void putsDiferentDataTypes() {

        ht.put("0", 0.1f);       //   Inserir un element de tipus float
        ht.put("1", true);            //  Inserir un element de tipus boolean
        ht.put("2", llista.get(0));                    //  Inserir un objecte de la classe ClasseDeProba que conté un metode to string que retorna un asterisc
        ht.put("3", llista);                        //    Inserir un element de tipus List que retornara asteriscs
        /*ht.put("4", array);*/            // inserir un element de tipus array que també retornarà asteriscs
        Assertions.assertEquals(ht.toString(), "\n" +
                " bucket[0] = [0, 0.1]\n" +
                " bucket[1] = [1, true]\n" +
                " bucket[2] = [2, *]\n" +
                " bucket[3] = [3, [*, *, *]]"/* +
                " bucket[4] = [4, [*, *, *]]"*/);                     // comprova que funcionen els puts
        Assertions.assertEquals(4, ht.count());     // comprova que funciona el count
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size

    }



    @Test
    void objectUpdates() {
        ht = new HashTable();
        ht.put("1", 1);
        ht.put("1", 10);    // Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula buida.
        ht.put("0", 0);
        ht.put("0", 3);    // Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no buida.
        ht.put("11", 11);
        ht.put("22", 22);
        ht.put("11", 1);  //  Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no buida.
        ht.put("22", 2);//   Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no buida.
        Assertions.assertEquals( "\n bucket[0] = [0, 3] -> [11, 1] -> [22, 2]\n" +
                " bucket[1] = [1, 10]",ht.toString());
        Assertions.assertEquals(4, ht.count());
        //    Resultat abans d'arreglar el codi
        // bucket[0] = [0, 0] -> [0, 00] -> [11, 11] -> [11, 00] -> [22, 22] -> [22, 00]           -s'estan afegint elements en comptes de fer un update-
        // bucket[1] = [1, 1] -> [1, 0]

        //    Resultat abans d'arreglar el codi
        //      ht.count() =  8                                                                     - no s'han d'augmentar els items quan es fa un update -
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size
    }

    @Test
    void updatesDiferentDataTypes() {
        ht = new HashTable();
        ht.put("0",1);
        ht.put("1", false);
        ht.put("1", true);
        Assertions.assertEquals(true,ht.get("1"));
        ht.put("1", '3');
        Assertions.assertEquals('3',ht.get("1"));
        ht.put("1", llista.get(0));
        Assertions.assertEquals("*",ht.get("1").toString());
        ht.put("1", llista);
        Assertions.assertEquals("[*, *, *]",ht.get("1").toString());
        /*ht.put("1", array);*/  //  Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no buida.
        Assertions.assertEquals( "\n" +
                " bucket[0] = [0, 1]\n" +
                " bucket[1] = [1, [*, *, *]]",ht.toString());
        Assertions.assertEquals(2, ht.count());
        //    Resultat abans d'arreglar el codi
        // bucket[0] = [0, 0] -> [0, 00] -> [11, 11] -> [11, 00] -> [22, 22] -> [22, 00]           -s'estan afegint elements en comptes de fer un update-
        // bucket[1] = [1, 1] -> [1, 0]

        //    Resultat abans d'arreglar el codi
        //      ht.count() =  8                                                                     - no s'han d'augmentar els items quan es fa un update -
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size
    }



    @Test
    void objectGets() {
        ht = new HashTable();
        ht.put("0", 0);
        Assertions.assertEquals(0, ht.get("0")); // Obtenir un element que no col·lisiona dins una taula vuida.
        ht.put("1", 1);
        Assertions.assertEquals(1, ht.get("1")); // Obtenir un element que no col·lisiona dins una taula no buida.
        ht.put("11", 11);
        Assertions.assertEquals(0, ht.get("0")); //       Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        Assertions.assertEquals(11, ht.get("11")); //Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("22", 22);
        Assertions.assertEquals(22, ht.get("22"));//Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        Assertions.assertEquals(null, ht.get("2"));//Obtenir un elements que no existeix perquè la seva posició està buida.
        Assertions.assertEquals(null, ht.get("12"));// Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        // Resultat abans de fer cambis al codi
        //         NullPointerException
        Assertions.assertEquals(null, ht.get("33")); //       Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals(4, ht.count());     // comprova que funciona el count
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size
    }

    @Test
    void getsDiferentDataTypes() {
        ht = new HashTable();
        ht.put("0", true);
        Assertions.assertEquals(true, ht.get("0")); // Obtenir un element que no col·lisiona dins una taula vuida.
        ht.put("1", '1');
        Assertions.assertEquals('1', ht.get("1")); // Obtenir un element que no col·lisiona dins una taula no buida.
        ht.put("11", "11");
        Assertions.assertEquals(true, ht.get("0")); //       Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        Assertions.assertEquals("11", ht.get("11")); //Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("22", llista.get(0));
        Assertions.assertEquals("*", ht.get("22").toString());//Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.put("33",llista);
        Assertions.assertEquals(llista,ht.get("33"));
        ht.put("2",array);
        Assertions.assertEquals(array,ht.get("2"));

        Assertions.assertEquals(6, ht.count());     // comprova que funciona el count
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size
    }


    @Test
    void objectDrops() {
        ht= new HashTable();
        ht.put("1",1);
        ht.drop("1");// Esborrar un element que no col·lisiona dins una taula.
        ht.put("0",0);
        ht.put("11",11);
        ht.put("22",22);
        ht.put("33",33);
        ht.drop("0");//  Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
        ht.drop("22");// Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("0",0);
        ht.drop("33");// Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.drop("1");// Eliminar un elements que no existeix perquè la seva posició està buida.
        ht.put("1",1);
        ht.drop("12");// Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        ht.put("22",22);
        ht.drop("33");// Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals("\n bucket[0] = [11, 11] -> [0, 0] -> [22, 22]\n" +
                " bucket[1] = [1, 1]",ht.toString());      // comprova que els elements estan on toca i que s'han esborrat els corresponents

        Assertions.assertEquals(4, ht.count());     // comprova que funciona el count

        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size

    }

    @Test
    void dropsDiferentDataTypes() {
        ht= new HashTable();
        ht.put("1",1);
        ht.drop("1");// Esborrar un element que no col·lisiona dins una taula.
        ht.put("2",true);
        ht.drop("2");
        ht.put("3",array);
        ht.drop("3");
        ht.put("4",array[0]);
        ht.drop("4");
        ht.put("5",llista);
        ht.drop("5");
        ht.put("6",'a');
        ht.drop("6");

        Assertions.assertEquals("",ht.toString());      // comprova que els elements estan on toca i que s'han esborrat els corresponents
        Assertions.assertEquals(0, ht.count());     // comprova que funciona el count
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size

    }





}
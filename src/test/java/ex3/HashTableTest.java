package ex3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {
    HashTable ht = new HashTable();


    @Test
    void puts() {

        ht.put("0", "0");       //   Inserir un element que no col·lisiona dins una taula vuida.
        ht.put("1", "1");            //  Inserir un element que no col·lisiona dins una taula no vuida.
        ht.put("11", "11");                    //  Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
        ht.put("22", "22");                        //    Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
        Assertions.assertEquals(ht.toString(), "\n bucket[0] = [0, 0] -> [11, 11] -> [22, 22]\n" +
                " bucket[1] = [1, 1]");                     // comprova que funcionen els puts
        Assertions.assertEquals(4, ht.count());     // comprova que funciona el count
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size

        // Resultat de la proba abans de fer els cambis:
        //          ht.count() = 0         -no augmenten els items al fer un put-

    }

    @Test
    void updates() {
        ht = new HashTable();
        ht.put("1", "1");
        ht.put("1", "10");    // Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula buida.
        ht.put("0", "0");
        ht.put("0", "00");    // Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no buida.
        ht.put("11", "11");
        ht.put("22", "22");
        ht.put("11", "01");  //  Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no buida.
        ht.put("22", "02");//   Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no buida.
        Assertions.assertEquals( "\n bucket[0] = [0, 00] -> [11, 01] -> [22, 02]\n" +
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
    void gets() {
        ht = new HashTable();
        ht.put("0", "0");
        Assertions.assertEquals("0", ht.get("0")); // Obtenir un element que no col·lisiona dins una taula vuida.
        ht.put("1", "1");
        Assertions.assertEquals("1", ht.get("1")); // Obtenir un element que no col·lisiona dins una taula no buida.
        ht.put("11", "11");
        Assertions.assertEquals("0", ht.get("0")); //       Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        Assertions.assertEquals("11", ht.get("11")); //Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("22", "22");
        Assertions.assertEquals("22", ht.get("22"));//Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        Assertions.assertEquals(null, ht.get("2"));//Obtenir un elements que no existeix perquè la seva posició està buida.
        Assertions.assertEquals(null, ht.get("12"));// Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        // Resultat abans de fer cambis al codi
        //         NullPointerException
        Assertions.assertEquals(null, ht.get("33")); //       Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals(4, ht.count());     // comprova que funciona el count
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size
    }


    @Test
    void drops() {
        ht= new HashTable();
        ht.put("1","1");
        ht.drop("1");// Esborrar un element que no col·lisiona dins una taula.
        ht.put("0","0");
        ht.put("11","11");
        ht.put("22","22");
        ht.put("33","33");
        ht.drop("0");//  Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
        ht.drop("22");// Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("0","0");
        ht.drop("33");// Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.drop("1");// Eliminar un elements que no existeix perquè la seva posició està buida.
        ht.put("1","1");
        ht.drop("12");// Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        ht.put("22","22");
        ht.drop("33");// Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals("\n bucket[0] = [11, 11] -> [0, 0] -> [22, 22]\n" +
                " bucket[1] = [1, 1]",ht.toString());      // comprova que els elements estan on toca i que s'han esborrat els corresponents
        // bucket[0] = [11, 11] -> [0, 0] -> [22, 22]
        // bucket[1] = [1, 1]
        Assertions.assertEquals(4, ht.count());     // comprova que funciona el count
        // Resultat abans dels cambis al codi
        //          ht.count() = 8
        Assertions.assertEquals(16, ht.size());      // comprova que funciona el size

    }



}
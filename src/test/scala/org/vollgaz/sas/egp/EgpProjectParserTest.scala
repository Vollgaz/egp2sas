package org.vollgaz.sas.egp

import org.scalatest.{FlatSpec, Matchers}

class EgpProjectParserTest extends FlatSpec with Matchers {

    val parser = new EgpProjectParser("/my/fake/path")
    /*
        it should "Verify it can open zip" in {
            val parser: String = new EgpProjectParser().parseStream(new FileInputStream("src/test/resources/project-KPI-Telephonie.xml"))
            val filew: FileWriter = new FileWriter("target/sasout.sas")
            filew.write(parser)
            filew.close()
        }
    */

    it should "Verify the regex for filepath" in {
        val submittedCode =
            """
/* --------------------------------------------------------------------
Code généré par une tâche SAS

Généré sur lundi 8 juillet 2019 à 11:23:55
Par la tâche :     Assistant Importer les données

Fichier source : G:\apps\bidule\machin - much\30 - Projet
SAS\91 - KPI DU VIDE \Codification.xlsx
Serveur :      Système de fichiers local

Données de sortie : WORK.Codification reglements
Serveur :      SASApp

Note: en préparation de l'exécution du code suivant, l'assistant
Importer les données a utilisé des routines internes pour
transférer le fichier de données source de le système de fichiers
local à SASApp. Il n'y a aucun code SAS disponible pour représenter
cette action.
Certains caractères incorporés dans les données de la feuille de
calcul ont été traduits par des caractères de remplacement afin
d'éviter des erreurs de transmission.
-------------------------------------------------------------------- */

/* --------------------------------------------------------------------
Cette étape DATA lit les valeurs des données d'un fichier texte
temporaire créé par l'assistant Importer les données. Les valeurs
du fichier texte temporaire ont été extraites du fichier source
Excel.
-------------------------------------------------------------------- */

DATA WORK.'Codification'n;
LENGTH
'Code objet'n    $ 3
'Libelle francais'n $ 86
'Libelle allemand'n $ 84 ;
FORMAT
'Code objet'n    $CHAR3.
'Libelle francais'n $CHAR86.
'Libelle allemand'n $CHAR84. ;
INFORMAT
'Code objet'n    $CHAR3.
'Libelle francais'n $CHAR86.
'Libelle allemand'n $CHAR84. ;
INFILE '/opt/sas/work/SAS_work1C320000375C_sfpl-sasmeta-01/#LN00131'
"""
        parser.correctFileImport(submittedCode) should be(
            """
/* --------------------------------------------------------------------
Code généré par une tâche SAS

Généré sur lundi 8 juillet 2019 à 11:23:55
Par la tâche :     Assistant Importer les données

Fichier source : G:\apps\bidule\machin - much\30 - Projet
SAS\91 - KPI DU VIDE \Codification.xlsx
Serveur :      Système de fichiers local

Données de sortie : WORK.Codification reglements
Serveur :      SASApp

Note: en préparation de l'exécution du code suivant, l'assistant
Importer les données a utilisé des routines internes pour
transférer le fichier de données source de le système de fichiers
local à SASApp. Il n'y a aucun code SAS disponible pour représenter
cette action.
Certains caractères incorporés dans les données de la feuille de
calcul ont été traduits par des caractères de remplacement afin
d'éviter des erreurs de transmission.
-------------------------------------------------------------------- */

/* --------------------------------------------------------------------
Cette étape DATA lit les valeurs des données d'un fichier texte
temporaire créé par l'assistant Importer les données. Les valeurs
du fichier texte temporaire ont été extraites du fichier source
Excel.
-------------------------------------------------------------------- */

DATA WORK.'Codification'n;
LENGTH
'Code objet'n    $ 3
'Libelle francais'n $ 86
'Libelle allemand'n $ 84 ;
FORMAT
'Code objet'n    $CHAR3.
'Libelle francais'n $CHAR86.
'Libelle allemand'n $CHAR84. ;
INFORMAT
'Code objet'n    $CHAR3.
'Libelle francais'n $CHAR86.
'Libelle allemand'n $CHAR84. ;
INFILE "/my/fake/path/Codification.xlsx"
""")
    }
}

% KOMPONIST(KNR, VORNAME, NAME, GEBOREN, GESTORBEN).

% KNR ist Schluessel.
% null-Werte sind möglich
% Tupel 11 - 51.

komponist(11, 'Georg Friedrich', 'Händel', 1685, 1759).
komponist(12, 'Serge', 'Prokofiev', 1891, 1953).
komponist(13, 'Claudio', 'Monteverdi', 1567, 1643).
komponist(14, 'Antonin', 'Dvorak', 1841, 1904).
komponist(15, 'Antonio', 'Vivaldi', 1678, 1741).
komponist(16, 'Leopold', 'Mozart', 1719, 1787).
komponist(17, 'Heinrich Ignaz Franz', 'Biber', 1644, 1704).
komponist(18, 'Georg Philipp', 'Telemann', 1681, 1767).
komponist(19, 'William', 'Byrd', 1543, 1623).
komponist(20, 'Wolfgang Amadeus', 'Mozart', 1756, 1791).
komponist(21, 'Ludwig van', 'Beethoven', 1770, 1827).
komponist(22, 'Johann Sebastian', 'Bach', 1685, 1750).
komponist(23, 'Gustav', 'Mahler', 1860, 1911).
komponist(24, 'Jean', 'Sibelius', 1865, 1957).
komponist(25, 'Hector', 'Berlioz', 1803, 1869).
komponist(26, 'Joseph', 'Haydn', 1732, 1809).
komponist(27, 'Peter', 'Tschaikowsky', 1840, 1893).
komponist(28, 'Felix', 'Mendelssohn Bartholdy', 1809, 1847).
komponist(29, 'Franz', 'Schubert', 1797, 1828).
komponist(30, 'Carl', 'Orff', 1895, 1982).
komponist(null, 'error', 'exception', 1342, 1388).
komponist(null, 'error', 'exception', null, null).
komponist(31, 'Dmitri', 'Schostakowitsch', 1906, 1975).
komponist(32, 'Josquin', 'Desprez', 1440, 1521).
komponist(33, 'Ermanno', 'Wolf-Ferrari', 1876, 1948).
komponist(34, 'Johannes', 'Brahms', 1833, 1897).
komponist(35, 'Benedetto', 'Marcello', 1686, 1739).
komponist(36, 'Lean-Marie', 'Leclair', 1697, 1764).
komponist(37, 'Arcangelo', 'Corelli', 1653, 1713).
komponist(38, 'Tommaso', 'Albinoni', 1671, 1750).
komponist(39, 'Domenico', 'Scarlatti', 1685, 1757).
komponist(40, 'Francesco', 'Manfredini', 1680, 1748).
komponist(41, 'Pietro', 'Locatelli', 1695, 1764).
komponist(42, 'Melchior', 'Franck', 1580, 1639).
komponist(43, 'Hans', 'Heselloher', 1420, 1485).
komponist(44, 'Giorgio', 'Mainerio', 1545, 1582).
komponist(45, 'Giacomo', 'Fogliano', 1468, 1548).
komponist(46, 'Heinrich', 'Isaak', 1450, 1517).
komponist(47, 'Giovan Domenico', 'Da Nola', 1510, 1592).
komponist(48, 'Filippo', 'Azzaiolo', 1530, 1569).
komponist(49, 'Tilman', 'Susato', 1500, 1561).
komponist(50, 'Clement', 'Janequin', 1485, 1558).
komponist(51, null, null, 4288, 8842).
komponist(52, null, 'Boulez', 4288, 8842).

% STUECK(SNR, KNR, TITEL, TONART, OPUS).

% SNR ist Schluessel.
% KNR ist Fremdschluessel, der auf KOMPONIST verweist.
% null-Werte sind möglich
% Tupel 1001 - 1178.

stueck(1001, 11, 'Concerto grosso op.6 Nr.7', 'B-dur', 'Op.6 Nr.7').
stueck(1002, 11, 'Feuerwerks-Musik (Concerto grosso)', 'D-dur', null).
stueck(1003, 11, 'Concerto grosso op.6 Nr.8', 'c-moll', 'Op.6 Nr.8').
stueck(1004, 11, 'Sinfonia e-moll (aus `Der Messias\')', 'e-moll', null).
stueck(1005, 12, 'Konzert fuer Violine und Orchester Nr.1', 'D-dur', 'Op.19').
stueck(1006, 12, 'Konzert fuer Violine und Orchester Nr.2', 'g-moll', 'Op.63').
stueck(1007, 13, 'Verspro Della Beata Vergine', null, null).
stueck(1008, 14, 'Slawische Taenze op.46', null, 'Op.46 Nr.1-8').
stueck(1009, 14, 'Slawische Taenze op.72', null, 'Op.72 Nr.1-8').
stueck(1010, 15, 'Der Fruehling', 'E-dur', 'Op.8 Nr.1 RV269').
stueck(1011, 15, 'Der Sommer', 'g-moll', 'Op.8 Nr.2 RV315').
stueck(1012, 15, 'Der Herbst', 'F-dur', 'Op.8 Nr.3 RV293').
stueck(1013, 15, 'Der Winter', 'f-moll', 'Op.8 Nr.4 RV297').
stueck(1014, 16, 'Sinfonia D-Dur mit Dudelsack und Drehleier', 'D-dur', null).
stueck(1015, 17, 'Serenada C-Dur mit dem Nachwächterlied', 'C-dur', null).
stueck(1016, null, 'Kleine Stücke für Dudelsack und Drehleier', null, null).
stueck(1017, 15, 'Concerto per archi e cembalo', 'G-dur', null).
stueck(1018, 15, 'Sinfonia C-Dur', 'C-dur', null).
stueck(1019, 15, 'Concerto per violino e archi', 'a-moll', 'Op.3 Nr.6').
stueck(1020, 17, 'Sonata III a 5 Violae', null, null).
stueck(1021, 17, 'Sonata prima a 8, 2 Clarini, 6 Violae', null, null).
stueck(1022, 17, 'Ballettae a 4 Violettae', null, null).
stueck(1023, 17, 'Battalia', null, null).
stueck(1024, 17, 'Sonata a 7, 6 Tromb, Tamburin con Organo', null, null).
stueck(1025, 17, 'Sonata IV a 5 Violae', null, null).
stueck(1026, 17, 'Sonata seconda a 8, 2 Clarini, 6 Violae', null, null).
stueck(1027, 17, 'Sonata a 6 (die Pauern Kirchfahrt genandt)', null, null).
stueck(1028, 18, 'Overture in D (2 Trompeten, Pauken, ...)', 'D-dur', null).
stueck(1029, 18, 'Concerto in D (3 Trompeten, Pauken, Oboen,..)', 'D-dur', null).
stueck(1030, 18, 'Sinfonia in F (Trompete, 3 Posaunen, ...)', 'F-dur', null).
stueck(1031, 18, 'Concerto in D (Trompete, 2 Oboen, continuo)', 'D-dur', null).
stueck(1032, 18, 'Concerto in D (Trompete, 2 Oboen, ...)', 'D-dur', null).
stueck(1033, 18, 'Concerto in D (Trompete, Streicher, continuo)', 'D-dur', null).
stueck(1034, 18, 'Concerto in D (Trompete, Streicher, continuo)', 'D-dur', null).
stueck(1035, 18, 'Suite in D (Trompete, Streicher, continuo)', 'D-dur', null).
stueck(1036, 18, 'Concerto in Es (2 Trompeten, Streicher, cont)', 'Es-dur', null).
stueck(1037, 19, 'Mass for five voices', null, null).
stueck(1038, 19, 'Mass for four voices', null, null).
stueck(1039, 19, 'Mass for three voices', null, null).
stueck(1040, 19, 'Ave verum corpus', null, null).
stueck(1041, 21, 'Die Geschöpfe des Prometheus, Ouvertüre', null, 'Op.43').
stueck(1042, 20, 'Die Entführung aus dem Serail, Ouvertüre', null, 'KV 384').
stueck(1043, 21, 'Fidelio, Ouvertüre', null, 'Op.94').
stueck(1044, 20, 'Die Zauberflöte, Ouvertüre', null, 'KV 620').
stueck(1045, 21, 'Egmont, Ouvertüre', null, 'Op.84').
stueck(1046, 20, 'Die Hochzeit des Figaro, Ouvertüre', null, 'KV 492').
stueck(1047, 21, 'Leonore III, Ouvertüre', null, 'Op.72b').
stueck(1048, 20, 'Titus, Ouvertüre', null, 'KV 621').
stueck(1049, 21, 'Coriolan, Ouvertüre', null, 'Op.62').
stueck(1050, 13, 'Cantate Domine - Motet', null, null).
stueck(1051, 13, 'Mass for four voices (1650)', null, null).
stueck(1052, 13, 'Domine, ne in furore - Motet', null, null).
stueck(1053, 13, 'Mass `In illo tempore\' for six voices (1610)', null, null).
stueck(1054, 22, 'Violin Concerto a-minor', 'a-moll', 'BWV 1041').
stueck(1055, 22, 'Violin Concerto E-major', 'E-dur', 'BWV 1042').
stueck(1056, 22, 'Concerto d-minor for 2 Violins', 'd-moll', 'BWV 1043').
stueck(1057, 22, 'Konzert d-minor for Oboe and Violin', 'd-moll', 'BWV 1060').
stueck(1058, 12, 'Symphonie Nr.1 D-dur `Symphonie classique''','D-dur', 'Op.25').
stueck(1059, 12, 'Symphonie Nr.5 B-dur', 'B-dur', 'Op.100').
stueck(1060, 23, 'Sinfonie Nr.4 G-dur', 'G-dur', null).
stueck(1061, 24, 'Symphonie Nr.2 D-dur', 'D-dur', 'Op.43').
stueck(1062, 24, '`Finlandia\'', null, 'Op.26').
stueck(1063, 24, '`Valse triste\'', null, 'Op.44').
stueck(1064, 24, '`The Swan of Tuonela\'', null, 'Op.22 Nr.2').
stueck(1065, 11, 'Wassermusik, Suite in F-dur', 'F-dur', null).
stueck(1066, 11, 'Wassermusik, Suite in D/G-dur', null, null).
stueck(1067, 11, 'Concerto a due cori no.2', null, null).
stueck(1068, 11, 'Concerto a due cori no.3', null, null).
stueck(1069, 11, 'Concerto grosso op.3 Nr.1', 'B-dur', 'Op.3 Nr.1').
stueck(1070, 11, 'Concerto grosso op.3 Nr.2', 'B-dur', 'Op.3 Nr.2').
stueck(1071, 11, 'Concerto grosso op.3 Nr.3', 'G-dur', 'Op.3 Nr.3').
stueck(1072, 11, 'Concerto grosso op.3 Nr.4', 'F-dur', 'Op.3 Nr.4').
stueck(1073, 11, 'Concerto grosso op.3 Nr.5', 'd-moll', 'Op.3 Nr.5').
stueck(1074, 11, 'Concerto grosso op.3 Nr.6', 'D-dur', 'Op.3 Nr.6').
stueck(1075, 11, 'Concerto grosso op.6 Nr.1', 'G-dur', 'Op.6 Nr.1').
stueck(1076, 11, 'Concerto grosso op.6 Nr.2', 'F-dur', 'Op.6 Nr.2').
stueck(1077, 11, 'Concerto grosso op.6 Nr.3', 'e-moll', 'Op.6 Nr.3').
stueck(1078, 11, 'Concerto grosso op.6 Nr.4', 'a-moll', 'Op.6 Nr.4').
stueck(1079, 11, 'Concerto grosso op.6 Nr.5', 'D-dur', 'Op.6 Nr.5').
stueck(1080, 11, 'Concerto grosso op.6 Nr.6', 'g-moll', 'Op.6 Nr.6').
stueck(1081, 11, 'Concerto grosso op.6 Nr.9', 'F-dur', 'Op.6 Nr.9').
stueck(1082, 11, 'Concerto grosso op.6 Nr.10', 'd-moll', 'Op.6 Nr.10').
stueck(1083, 11, 'Concerto grosso op.6 Nr.11', 'A-dur', 'Op.6 Nr.11').
stueck(1084, 11, 'Concerto grosso op.6 Nr.12', 'b-moll', 'Op.6 Nr.12').
stueck(1085, 25, 'Symphonie Fantastique', null, 'Op.14a').
stueck(1086, 25, 'Harold in Italy', null, 'Op.16').
stueck(1087, 25, 'Overture - Rob Roy', null, null).
stueck(1088, 25, 'Overture - The Corsair', null, 'Op.21').
stueck(1089, 12, 'Cinderella (Suite)', null, null).
stueck(1090, 16, 'Hornkonzert D-dur', 'D-dur', 'KV 386b 412/514').
stueck(1091, 16, 'Hornkonzert Es-dur', 'Es-dur', 'KV 495').
stueck(1092, 16, 'Rondo in Es-dur', 'Es-dur', 'KV 371').
stueck(1093, 16, 'Hornkonzert Es-dur', 'Es-dur', 'KV 447').
stueck(1094, 16, 'Hornkonzert Es-dur', 'Es-dur', 'KV 417').
stueck(1095, 26, 'Cellokonzert C-dur', 'C-dur', 'Hob. VIIb: 1').
stueck(1096, 26, 'Cellokonzert D-dur', 'D-dur', 'Hob. VIIb: 2').
stueck(1097, 18, 'Violinkonzert Nr.4', 'E-dur', null).
stueck(1098, 18, 'Violinkonzert Nr.11', 'B-dur', null).
stueck(1099, 18, 'Violinkonzert Nr.8', 'G-dur', null).
stueck(1100, 18, 'Violinkonzert Nr.3', 'D-dur', null).
stueck(1101, 18, 'Violinkonzert Nr.9', 'g-moll', null).
stueck(1102, 20, 'Missa in c `Grosse Messe\'', 'c-moll', 'KV 427 (417a)').
stueck(1103, 27, 'Konzert für Violine und Orchester D-dur', 'D-dur', 'Op.35').
stueck(1104, 28, 'Konzert für Violine und Orchester e-moll','e-moll', 'Op.64').
stueck(1105, 22, 'Brandenburgisches Konzert Nr.1', 'F-dur', 'BWV 1046').
stueck(1106, 22, 'Brandenburgisches Konzert Nr.2', 'F-dur', 'BWV 1047').
stueck(1107, 22, 'Brandenburgisches Konzert Nr.3', 'G-dur', 'BWV 1048').
stueck(1108, 22, 'Brandenburgisches Konzert Nr.4', 'G-dur', 'BWV 1049').
stueck(1109, 22, 'Brandenburgisches Konzert Nr.5', 'D-dur', 'BWV 1050').
stueck(1110, 22, 'Brandenburgisches Konzert Nr.6', 'B-dur', 'BWV 1051').
stueck(1111, 29, 'Symphonie Nr.8 `Die Unvollendete\'', 'h-moll', 'D.759').
stueck(1112, 29, 'Symphonie Nr.5', 'B-dur', 'D.485').
stueck(1113, 29, '7 Walzer (aus Valses nobles op.77)', null, 'D.969').
stueck(1114, 29, 'Deutscher Tanz', 'C-dur', 'Op.33').
stueck(1115, 30, 'Die Kluge', null, null).
stueck(1116, 30, 'Der Mond', null, null).
stueck(1117, 12, 'The Love of the Three Oranges: Symponic Suite',null, 'Op.33a').
stueck(1118, 12, 'Lieutenant Kije: Symponic Suite', null, 'Op.60').
stueck(1119, 12, 'Symphonie Nr.7', null, 'Op.131').
stueck(1120, 12, 'Symphonie Nr.6', null, 'Op.111').
stueck(1121, 12, 'Symphonie Nr.4 (1930)', null, 'Op.47').
stueck(1122, 12, 'Symphonie Nr.4 (1947)', null, 'Op.112').
stueck(1123, 12, 'Symphonie Nr.2', null, 'Op.40').
stueck(1124, 12, 'Symphonie Nr.3', null, 'Op.44').
stueck(1125, 31, 'Symphonie Nr.9', 'Es-dur', 'Op.70').
stueck(1126, 31, 'Symphonie Nr.12 `1917\'', 'd-moll', 'Op.112').
stueck(1127, 13, 'Madrigali Amorosi (Liebeslieder)', null, null).
stueck(1128, 13, 'Madrigali Guerrieri (Landskechtslieder)', null, null).
stueck(1129, 32, 'Missa di dadi', null, null).
stueck(1130, 32, 'Missa `Faisant regretz\'', null, null).
stueck(1131, 33, 'Klaviertrio Nr.1', 'D-dur', 'Op.5').
stueck(1132, 33, 'Klaviertrio Nr.2', 'Fis-dur', 'Op.7').
stueck(1133, 33, 'Klavierquintett', 'Des-dur', 'Op.6').
stueck(1134, 33, 'Streichquintett', 'C-dur', 'Op.24').
stueck(1135, 34, 'Ungarische Tänze Nr.1,3,10,17-21', null, null).
stueck(1136, 34, 'Symphonie Nr.2', 'D-dur', 'Op.73').
stueck(1137, 11, '`Messias\' (Höhepunkte)', null, null).
stueck(1138, 20, '`Salzburger Sinfonie\' Nr.1', 'D-dur', 'KV 136').
stueck(1139, 20, '`Salzburger Sinfonie\' Nr.2', 'B-dur', 'KV 137').
stueck(1140, 20, '`Salzburger Sinfonie\' Nr.3', 'F-dur', 'KV 138').
stueck(1141, 20, '`Eine kleine Nachtmusik\'', 'G-dur', 'KV 525').
stueck(1142, 15, 'Oboenkonzert a-moll', 'a-moll', null).
stueck(1143, 35, 'Oboenkonzert d-moll', 'd-moll', null).
stueck(1144, 18, 'Oboenkonzert c-moll', 'c-moll', null).
stueck(1145, 36, 'Oboenkonzert C-dur', 'C-dur', null).
stueck(1146, 37, 'Concerto grosso op.6/8', 'g-moll', 'Op.6/8').
stueck(1147, 38, 'Concerto a cinque', 'B-dur', null).
stueck(1148, 39, 'Sonata f-moll', 'f-moll', null).
stueck(1149, 40, 'Concerto grosso op.3/12', 'C-dur', 'Op.3/12').
stueck(1150, 41, 'Concerto grosso op.1/8', 'f-moll', 'Op.1/8').
stueck(1151, 39, 'Sonata G-dur', 'G-dur', null).
stueck(1152, 37, 'Concerto grosso D-dur', 'D-dur', null).
stueck(1153, 13, 'L\'Orfeo', null, null).
stueck(1154, 13, 'Il Ritorno d\'Ulisse in Patria', null, null).
stueck(1155, 13, 'L\'Incoronazione di Poppea', null, null).
stueck(1156, 42, 'Intrada', null, null).
stueck(1157, 43, 'Von üppiklichen Dingen', null, null).
stueck(1158, 44, 'Ballo francese', null, null).
stueck(1159, 45, 'L\'armor, dona, ch\'io te porto', null, null).
stueck(1160, null, 'Mit ganzem Willen (ca.1460)', null, null).
stueck(1161, 46, 'Questo mostrarsi adirata di fore', null, null).
stueck(1162, null, 'Passamezzo antico `Caminata\' (ca.1510)', null, null).
stueck(1163, null, 'Es hett ein schwab ein töchterlein (ca.1530)', null, null).
stueck(1164, 46, 'Fortuna desperata', null, null).
stueck(1165, null, 'Il marchese do Soluzzo (ca.1550)', null, null).
stueck(1166, null, 'Merce te chiamo (15.Jh.)', null, null).
stueck(1167, 47, 'Madonna nui sapimo', null, null).
stueck(1168, 46, 'Maudit soit', null, null).
stueck(1169, null, 'Passamezzo moderno (ca.1510)', null, null).
stueck(1170, 47, 'Chi la gagliarda', null, null).
stueck(1171, null, 'Der heilig herr sanct Matheis (ca.1515)', null, null).
stueck(1172, 45, 'Io vorrei dio d\'amore', null, null).
stueck(1173, 50, 'Il estoit une filette', null, null).
stueck(1174, null, 'Rodrigo Martines (ca.1500)', null, null).
stueck(1175, null, 'Dindirindin', null, null).
stueck(1176, 32, 'Scaramella', null, null).
stueck(1177, 48, 'Ti parti cor mio caro', null, null).
stueck(1178, 49, 'Bergerette Sans roch', null, null).
stueck(null, 88, 'error', 'exception', 'Op.4/2').


% CD(CDNR, NAME, HERSTELLER, ANZ_CDS, GESAMTSPIELZEIT).

% CDNR ist Schluessel.
% null-Werte sind möglich
% Tupel 101 - 143.

cd(101, 'Händel: Feuerwerksmusik', 'zyx classic', 1, 60).
cd(102, 'Serge Prokofiev: Die Violinkonzerte', 'DG', 1, 49).
cd(103, 'Monteverdi: Vespro Della Beata Vergine 1610', 'Decca', 2, 100).
cd(104, 'Antonin Dvorak: Slawische Tänze', 'DG', 1, 70).
cd(105, 'Vivaldi: Le Quattro Stagioni (I Musici)', 'Philips', 1, 42).
cd(106, 'Leopold Mozart: Sinfonia D-Dur ...', 'Orfeo', 1, 44).
cd(107, 'Vivaldi: Die vier Jahreszeiten', 'zyx classic', 1, 64).
cd(108, 'Heinrich Ignaz Franz Biber: Battalia ...', 'Teldec', 1, 51).
cd(109, 'Telemann: Trompetenkonzerte', 'Nimbus Records', 1, 76).
cd(110, 'William Byrd: The Three Masses', 'Gimell', 1, 67).
cd(111, 'Mozart/Beethoven: Klassische Ouvertüren', 'zyx classic', 1, 64).
cd(112, 'Monteverdi: Mass for four voices, ...', 'Hyperion', 1, 57).
cd(113, 'Bach: Violinkonzerte', 'Philips', 1, 61).
cd(114, 'Sergej Prokofiew: Symphonie Classique ...', 'DG', 1, 57).
cd(115, 'Gustav Mahler: Sinfonie Nr.4 G-dur', 'Saphir', 1, 57).
cd(116, 'Sibelius: Symphony No.2 ...', 'Philips', 1, 69).
cd(null, 'null: pointer exception ...', 'Philips', 42, 88).
cd(117, 'G.F.Handel: Orchesterwerke', 'Archiv Produktion', 6, 342).
cd(118, 'Berlioz: Symphonie Fantastique', 'Meastro', 1, 49).
cd(119, 'Berlioz: Harold in Italy', 'Decca', 1, 66).
cd(120, 'Prokofiev: Cinderella (Suite)', 'RCA', 1, 49).
cd(121, 'Mozart: Hornkonzerte', 'Philips', 1, 60).
cd(122, 'Haydn: Cellokonzerte', 'Philips', 1, 47).
cd(123, 'Telemann: 5 Violinkonzerte', 'Philips', 1, 51).
cd(124, 'Mozart: Grosse Messe in c-moll', 'Teldec', 1, 56).
cd(125, 'Tschaikowsky/Mendelssohn: Violinkonzerte', 'DG', 1, 58).
cd(126, 'Brandenburg Concertos No.1, 3, 4 and 6', 'Spectrum', 1, 72).
cd(127, 'Brandenburgische Konzerte', 'zyx classic', 1, 65).
cd(128, 'Schubert: Die Unvollendete', 'zyx classic', 1, 64).
cd(129, 'Carl Orff: Die Kluge / Der Mond', 'eurodisc', 2, 158).
cd(130, 'Prokofiev: Classical Symphony ...', 'EMI', 1, 53).
cd(131, 'Prokofiev: Les Symphonies', 'ERATO', 4, 280).
cd(132, 'Schostakowitsch: Symphonien 9+12', 'eurodisc', 1, 68).
cd(133, 'Claudio Monteverdi: Madrigali Amorosi ...', 'Intercord', 1, 39).
cd(134, 'Josquin Desprez: Missa di dadi ...', 'L\'oiseau-Lyre', 1, 52).
cd(135, 'Wolf-Ferrari: Kammermusik', 'Dabringhaus/Grimm', 2, 118).
cd(136, 'Brahms: Ungarische Tänze', 'zyx classic', 1, 60).
cd(137, 'G. F. Händel: `Messias\' (Höhepunkte)', 'DD classique', 1, 53).
cd(138, 'Mozart: Salzburger Sinfonien', 'zyx classic', 1, 61).
cd(139, 'Oboenkonzerte', 'Da Camera Magna', 1, 43).
cd(140, 'Corelli,Albinoni,Scarlatti,Manfredini,...', 'zyx classic', 1, 66).
cd(141, 'Claudio Monteverdi: L\'Orfeo, Ulisse, Poppea', 'Teldec', 6, 409).
cd(142, 'Schlager um 1500', 'Christophorus', 1, 63).
cd(143, null, 'pointer exception', 42, 88).

% AUFNAHME(CDNR, SNR, ORCHESTER, LEITUNG).

% CDNR und SNR zusammen sind Schluessel.
% CDNR verweist auf CD, SNR auf STUECK.
% null-Werte sind möglich
% Tupel 101,1001 - 142, 1178.

aufnahme(101, 1001, 'London Festival Orchestra', 'Sidney Lark').
aufnahme(101, 1002, 'London Festival Orchestra', 'Sidney Lark').
aufnahme(101, 1003, 'London Festival Orchestra', 'Sidney Lark').
aufnahme(101, 1004, 'Radio-Sinfonieorchester Stuttgart', 'Garcia Navarro').
aufnahme(102, 1005, 'Chicago Symphony Orchestra', 'Claudio Abbado').
aufnahme(102, 1006, 'Chicago Symphony Orchestra', 'Claudio Abbado').
aufnahme(103, 1007, 'The Monteverdi Choir and Orchestra', 'John Eliot Gardiner').
aufnahme(104, 1008, 'Symponie-Orchester des Bayerischen Rundfunks', 'Rafael Kubelik').
aufnahme(104, 1009, 'Symponie-Orchester des Bayerischen Rundfunks', 'Rafael Kubelik').
aufnahme(105, 1010, 'I Musici', null).
aufnahme(105, 1011, 'I Musici', null).
aufnahme(105, 1012, 'I Musici', null).
aufnahme(105, 1013, 'I Musici', null).
aufnahme(106, 1014, 'Münchner Kammerorchester', 'Hans Stadlmair').
aufnahme(106, 1015, 'Münchner Kammerorchester', 'Hans Stadlmair').
aufnahme(106, 1016, null, null).
aufnahme(107, 1010, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(107, 1011, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(107, 1012, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(107, 1013, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(107, 1017, 'Die Zagreber Solisten', null).
aufnahme(107, 1018, 'Die Zagreber Solisten', null).
aufnahme(107, 1019, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(108, 1020, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(108, 1021, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(108, 1022, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(108, 1023, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(108, 1024, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(108, 1025, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(108, 1026, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(108, 1027, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(109, 1028, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1029, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1030, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1031, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1032, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1033, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1034, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1035, 'English String Orchestra', 'William Boughton').
aufnahme(109, 1036, 'English String Orchestra', 'William Boughton').
aufnahme(110, 1037, 'The Tallis Scholars', 'Peter Phillips').
aufnahme(110, 1038, 'The Tallis Scholars', 'Peter Phillips').
aufnahme(110, 1039, 'The Tallis Scholars', 'Peter Phillips').
aufnahme(110, 1040, 'The Tallis Scholars', 'Peter Phillips').
aufnahme(111, 1041, 'London Festival Orchestra', 'Kurt Redel').
aufnahme(111, 1042, 'London Philharmonic Orchestra', 'Alfred Scholz').
aufnahme(111, 1043, 'Radio Symphonieorchester', 'Anton Nanut').
aufnahme(111, 1044, 'London Philharmonic Orchestra', 'Alfred Scholz').
aufnahme(111, 1045, 'London Symphony Orchestra', 'Alfred Scholz').
aufnahme(111, 1046, 'London Philharmonic Orchestra', 'Alfred Scholz').
aufnahme(111, 1047, 'Radio Symphonieorchester', 'Anton Nanut').
aufnahme(111, 1048, 'London Festival Orchestra', 'Kurt Redel').
aufnahme(111, 1049, 'London Philharmonic Orchestra', 'Alfred Scholz').
aufnahme(null, 1049, 'null pointer', 'exception').
aufnahme(111, null, 'null pointer', 'exception').
aufnahme(null, null, 'null pointer', 'exception').
aufnahme(112, 1050, 'The Sixteen', 'Harry Christophers').
aufnahme(112, 1051, 'The Sixteen', 'Harry Christophers').
aufnahme(112, 1052, 'The Sixteen', 'Harry Christophers').
aufnahme(112, 1053, 'The Sixteen', 'Harry Christophers').
aufnahme(113, 1054, 'Les Solistes Romands', 'Arpad Gerecz').
aufnahme(113, 1055, 'Les Solistes Romands', 'Arpad Gerecz').
aufnahme(113, 1056, 'Les Solistes Romands', 'Arpad Gerecz').
aufnahme(113, 1057, 'New Philharmonia Orchestra', 'Edo De Waart').
aufnahme(114, 1058, 'Berliner Philharmoniker', 'Herbert von Karajan').
aufnahme(114, 1059, 'Berliner Philharmoniker', 'Herbert von Karajan').
aufnahme(115, 1060, 'Sinfonieorchester des Südwestfunks', 'Michael Gielen').
aufnahme(116, 1061, 'Boston Symphony Orchestra', 'Sir Colin Davis').
aufnahme(116, 1062, 'Boston Symphony Orchestra', 'Sir Colin Davis').
aufnahme(116, 1063, 'Boston Symphony Orchestra', 'Sir Colin Davis').
aufnahme(116, 1064, 'Boston Symphony Orchestra', 'Sir Colin Davis').
aufnahme(117, 1001, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1002, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1003, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1065, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1066, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1067, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1068, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1069, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1070, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1071, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1072, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1073, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1074, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1075, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1076, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1077, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1078, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1079, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1080, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1081, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1082, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1083, 'The English Concert', 'Trevor Pinnock').
aufnahme(117, 1084, 'The English Concert', 'Trevor Pinnock').
aufnahme(118, 1085, 'The Cleveland Orchestra', 'Lorin Maazel').
aufnahme(119, 1086, 'Orchestre symphonique de Montreal', 'Charles Dutoit').
aufnahme(119, 1087, 'Orchestre symphonique de Montreal', 'Charles Dutoit').
aufnahme(119, 1088, 'Orchestre symphonique de Montreal', 'Charles Dutoit').
aufnahme(120, 1089, 'Saint Louis Symphony Orchestra', 'Leonard Slatkin').
aufnahme(121, 1090, 'Academy of St.Martin-in-the-Fields', 'Sir Neville Marriner').
aufnahme(121, 1091, 'Academy of St.Martin-in-the-Fields', 'Sir Neville Marriner').
aufnahme(121, 1092, 'Academy of St.Martin-in-the-Fields', 'Sir Neville Marriner').
aufnahme(121, 1093, 'Academy of St.Martin-in-the-Fields', 'Sir Neville Marriner').
aufnahme(121, 1094, 'Academy of St.Martin-in-the-Fields', 'Sir Neville Marriner').
aufnahme(122, 1095, 'Academy of St.Martin-in-the-Fields', 'Sir Neville Marriner').
aufnahme(122, 1096, 'Academy of St.Martin-in-the-Fields', 'Sir Neville Marriner').
aufnahme(123, 1097, 'Academy of St.Martin-in-the-Fields', 'Iona Brown').
aufnahme(123, 1098, 'Academy of St.Martin-in-the-Fields', 'Iona Brown').
aufnahme(123, 1099, 'Academy of St.Martin-in-the-Fields', 'Iona Brown').
aufnahme(123, 1100, 'Academy of St.Martin-in-the-Fields', 'Iona Brown').
aufnahme(123, 1101, 'Academy of St.Martin-in-the-Fields', 'Iona Brown').
aufnahme(124, 1102, 'Concentus musicus Wien', 'Nikolaus Harnoncourt').
aufnahme(125, 1103, 'Wiener Philharmoniker', 'Claudio Abbado').
aufnahme(125, 1104, 'Wiener Philharmoniker', 'Claudio Abbado').
aufnahme(126, 1105, 'Camerata Würzburg', 'Prof. H. Reinartz').
aufnahme(126, 1107, 'Camerata Würzburg', 'Prof. H. Reinartz').
aufnahme(126, 1108, 'Camerata Würzburg', 'Prof. H. Reinartz').
aufnahme(126, 1110, 'Camerata Würzburg', 'Prof. H. Reinartz').
aufnahme(127, 1105, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(127, 1106, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(127, 1107, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(127, 1109, 'Südwest-Studioorchester', 'Heribert Münchner').
aufnahme(128, 1111, 'Philharm. Festspielorchester', 'Vladimir Petroschoff').
aufnahme(128, 1112, 'Philharm. Festspielorchester', 'Vladimir Petroschoff').
aufnahme(128, 1113, null, null).
aufnahme(128, 1114, 'Symponisches Orchester Berlin', 'Carl-August Bünte').
aufnahme(129, 1115, 'Münchner Rundfunkorchester', 'Kurt Eichhorn').
aufnahme(129, 1116, 'Münchner Rundfunkorchester', 'Kurt Eichhorn').
aufnahme(130, 1058, 'London Philharmonic Orchestra', 'Enrique Batiz').
aufnahme(130, 1117, 'London Philharmonic Orchestra', 'Enrique Batiz').
aufnahme(130, 1118, 'London Philharmonic Orchestra', 'Enrique Batiz').
aufnahme(131, 1058, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(131, 1059, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(131, 1119, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(131, 1120, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(131, 1121, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(131, 1122, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(131, 1123, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(131, 1124, 'Orchestre National De France', 'M. Rostropovitch').
aufnahme(132, 1125, 'Staatl. Sinfonie-Orch. des Kultusmin.d.UdSSR', 'G. Roshdestwenskij').
aufnahme(132, 1126, 'Staatl. Sinfonie-Orch. des Kultusmin.d.UdSSR', 'G. Roshdestwenskij').
aufnahme(133, 1127, 'Die Prager Madrigalisten/Musica Antiqua Wien', 'Venhoda/Clemencic').
aufnahme(133, 1128, 'Die Prager Madrigalisten/Musica Antiqua Wien', 'Venhoda/Clemencic').
aufnahme(134, 1129, 'The Medieval Ensemble of London', 'P.Davies/T.Davies').
aufnahme(134, 1130, 'The Medieval Ensemble of London', 'P.Davies/T.Davies').
aufnahme(135, 1131, 'Münchner Klaviertrio', null).
aufnahme(135, 1132, 'Münchner Klaviertrio', null).
aufnahme(135, 1133, 'Leopolder-Quartett München', null).
aufnahme(135, 1134, 'Leopolder-Quartett München', null).
aufnahme(136, 1135, 'London Festival Orchestra', 'Julian Armstrong').
aufnahme(136, 1136, 'London Festival Orchestra', 'Julian Armstrong').
aufnahme(137, 1137, 'Westminster Choir / New York Philharmonic', 'Leonard Bernstein').
aufnahme(138, 1138, 'Camerata Academia Salzburg', 'Hermann Abel').
aufnahme(138, 1139, 'Camerata Academia Salzburg', 'Hermann Abel').
aufnahme(138, 1140, 'Camerata Academia Salzburg', 'Hermann Abel').
aufnahme(138, 1141, 'Camerata Academia Salzburg', 'Alexander v. Pitamic').
aufnahme(139, 1142, 'Heidelberger Kammerorchester', null).
aufnahme(139, 1143, 'Heidelberger Kammerorchester', null).
aufnahme(139, 1144, 'Heidelberger Kammerorchester', null).
aufnahme(139, 1145, 'Heidelberger Kammerorchester', null).
aufnahme(140, 1146, 'Radio-Sinfonieorchester Stuttgart', 'Garcia Navarro').
aufnahme(140, 1147, 'Die Zagreber Solisten', null).
aufnahme(140, 1148, null, null).
aufnahme(140, 1149, 'Radio-Sinfonieorchester Stuttgart', 'Garcia Navarro').
aufnahme(140, 1150, 'Radio-Sinfonieorchester Stuttgart', 'Garcia Navarro').
aufnahme(140, 1151, null, null).
aufnahme(140, 1152, 'Die Zagreber Solisten', null).
aufnahme(141, 1153, 'Monteverdi Ensemble/Opernhaus Zürich',
                   'Nikolaus Harnocourt').
aufnahme(141, 1154, 'Monteverdi Ensemble/Opernhaus Zürich',
                   'Nikolaus Harnocourt').
aufnahme(141, 1155, 'Monteverdi Ensemble/Opernhaus Zürich',
                   'Nikolaus Harnocourt').
aufnahme(142, 1156, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1157, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1158, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1159, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1160, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1161, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1162, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1163, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1164, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1165, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1166, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1167, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1168, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1169, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1170, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1171, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1172, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1173, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1174, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1175, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1176, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1177, 'Ensemble für frühe Musik Augsburg', null).
aufnahme(142, 1178, 'Ensemble für frühe Musik Augsburg', null).

% SOLIST(CDNR, SNR, NAME, INSTRUMENT).

% NAME ist Schluessel.
% CDNR und SNR zusammen sind ein Fremdschluessel, der auf AUFNAHE verweist.
% null-Werte sind möglich
% Tupel 102 - 140.

solist(102, 1005, 'Shlomo Mintz', 'Violine').
solist(102, 1006, 'Shlomo Mintz', 'Violine').
solist(105, 1010, 'Pina Carmirelli', 'Violine').
solist(105, 1011, 'Pina Carmirelli', 'Violine').
solist(105, 1012, 'Pina Carmirelli', 'Violine').
solist(105, 1013, 'Pina Carmirelli', 'Violine').
solist(106, 1014, 'Jan Engel', 'Bagpipes').
solist(106, 1014, 'Max Engel', 'Drehleier').
solist(106, 1015, 'Kurt Moll', 'Bass').
solist(106, 1016, 'Jan Engel', 'Bagpipes').
solist(106, 1016, 'Max Engel', 'Drehleier').
solist(109, 1028, 'John Wallace', 'Trumpet').
solist(109, 1029, 'John Wallace', 'Trumpet').
solist(109, 1030, 'John Wallace', 'Trumpet').
solist(109, 1031, 'John Wallace', 'Trumpet').
solist(109, 1032, 'John Wallace', 'Trumpet').
solist(109, 1033, 'John Wallace', 'Trumpet').
solist(109, 1034, 'John Wallace', 'Trumpet').
solist(109, 1035, 'John Wallace', 'Trumpet').
solist(109, 1036, 'John Wallace', 'Trumpet').
solist(null, null, 'John Wallace', 'null pointer exception').
solist(109, 4288, null, 'null pointer exception').
solist(109, 8842, 'null pointer exception', null).
solist(109, null, 'null pointer', 'exception').
solist(null, 4288, 'null pointer', 'exception').
solist(113, 1054, 'Arthur Grumiaux', 'Violine').
solist(113, 1055, 'Arthur Grumiaux', 'Violine').
solist(113, 1056, 'Arthur Grumiaux', 'Violine').
solist(113, 1056, 'Herman Krebbers', 'Violine').
solist(113, 1057, 'Arthur Grumiaux', 'Violine').
solist(113, 1057, 'Heinz Holliger', 'Oboe').
solist(115, 1060, 'Christine Whittlesey', 'Sopran').
solist(115, 1060, 'Wolfgang Hock', 'Violine').
solist(116, 1064, 'Laurence Thorstenberg', 'English Horn').
solist(119, 1086, 'Pinchas Zukerman', 'Viola').
solist(121, 1090, 'Alan Civil', 'Horn').
solist(121, 1091, 'Alan Civil', 'Horn').
solist(121, 1092, 'Alan Civil', 'Horn').
solist(121, 1093, 'Alan Civil', 'Horn').
solist(121, 1094, 'Alan Civil', 'Horn').
solist(122, 1095, 'Heinrich Schiff', 'Cello').
solist(122, 1096, 'Heinrich Schiff', 'Cello').
solist(123, 1097, 'Iona Brown', 'Violine').
solist(123, 1098, 'Iona Brown', 'Violine').
solist(123, 1099, 'Iona Brown', 'Violine').
solist(123, 1100, 'Iona Brown', 'Violine').
solist(123, 1101, 'Iona Brown', 'Violine').
solist(124, 1102, 'Krisztina Laki', 'Sopran').
solist(124, 1102, 'Kurt Equiluz', 'Tenor').
solist(124, 1102, 'Robert Holl', 'Bass').
solist(124, 1102, 'Zsuzsanna Denes', 'Sopran').
solist(125, 1103, 'Nathan Milstein', 'Violine').
solist(125, 1104, 'Nathan Milstein', 'Violine').
solist(128, 1113, 'Isabel Mourao', 'Piano').
solist(129, 1115, 'Gottlob Frick', 'Bass').
solist(129, 1115, 'Lucia Popp', 'Sopran').
solist(129, 1115, 'Thomas Stewart', 'Bariton').
solist(129, 1116, 'Franz Crass', 'Bass').
solist(129, 1116, 'Fritz Strassner', 'Erzaehler').
solist(129, 1116, 'John van Kesteren', 'Tenor').
solist(135, 1134, 'Wolfgang Sawallisch', 'Piano').
solist(142, 1155, 'Kruemmel Monster', 'Kekse').
solist(142, 1163, 'Kruemmel Monster', 'Kekse').
solist(142, 1175, 'Kruemmel Monster', 'Kekse').
solist(139, 1142, 'Robin Williams', 'Oboe').
solist(139, 1143, 'Robin Williams', 'Oboe').
solist(139, 1144, 'Robin Williams', 'Oboe').
solist(139, 1145, 'Robin Williams', 'Oboe').
solist(140, 1148, 'Dubravka Tomsic', 'Piano').
solist(140, 1151, 'Dubravka Tomsic', 'Piano').
package lesson7.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class Tests {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    @Tag("Example")
    fun alignFile() {
        alignFile("input/align_in1.txt", 50, "temp.txt")
        assertFileContent(
            "temp.txt",
            """Для написания разных видов программ сейчас
применяются разные языки программирования.
Например, в сфере мобильных программ сейчас правят
бал языки Swift (мобильные устройства под
управлением iOS) и Java (устройства под
управлением Android). Системные программы, как
правило, пишутся на языках C или {cpp}. Эти же
языки долгое время использовались и для создания
встраиваемых программ, но в последние годы в этой
области набирает популярность язык Java. Для
написания web-клиентов часто используется
JavaScript, а в простых случаях -- язык разметки
страниц HTML. Web-серверы используют опять-таки
Java (в сложных случаях), а также Python и PHP (в
более простых). Наконец, простые desktop-программы
сейчас могут быть написаны на самых разных языках,
и выбор во многом зависит от сложности программы,
области её использования, предполагаемой
операционной системы. В первую очередь следует
назвать языки Java, {cpp}, C#, Python, Visual
Basic, Ruby, Swift.

Самым универсальным и одновременно самым
распространённым языком программирования на данный
момент следует считать язык Java. Java в широком
смысле -- не только язык, но и платформа для
выполнения программ под самыми разными
операционными системами и на разной аппаратуре.
Такая универсальность обеспечивается наличием
виртуальной машины Java -- системной программы,
интерпретирующей Java байт-код в машинные коды
конкретного компьютера или системы. Java также
включает богатейший набор библиотек для
разработки."""
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("8")
    fun deleteMarked() {
        deleteMarked("input/delete_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """Задачи _надо_ решать правильно,

и не надо при этом никуда торопиться___
            """.trimIndent()
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("14")
    fun countSubstrings() {
        assertEquals(
            mapOf("РАЗНЫЕ" to 2, "ные" to 2, "Неряшливость" to 1, "е" to 49, "эволюция" to 0),
            countSubstrings("input/substrings_in1.txt", listOf("РАЗНЫЕ", "ные", "Неряшливость", "е", "эволюция"))
        )
        assertEquals(
            mapOf("Карминовый" to 2, "Некрасивый" to 2, "белоглазый" to 1),
            countSubstrings("input/substrings_in1.txt", listOf("Карминовый", "Некрасивый", "белоглазый"))
        )
        assertEquals(
            mapOf("--" to 4, "ее" to 2, "животное" to 2, "." to 2),
            countSubstrings("input/substrings_in2.txt", listOf("--", "ее", "животное", "."))
        )
    }

    @Test
    @Tag("12")
    fun sibilants() {
        sibilants("input/sibilants_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """/**
 * Простая
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жУри, броШУра, параШут) в рамках данного задания обрабатывать не нужно
 *
 * жИ шИ ЖИ Ши ЖА шА Жа ша жу шу жу щу ча шу щу ща жа жи жи жу чу ча
 */"""
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("15")
    fun centerFile() {
        centerFile("input/center_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """              Съешь же ещё этих мягких французских булок, да выпей чаю.
Широкая электрификация южных губерний даст мощный толчок подъёму сельского хозяйства.
                                        Тест
                                          """ +  // Avoiding trailing whitespaces problem
                    """
                                     Hello World
           Во входном файле с именем inputName содержится некоторый текст.
        Вывести его в выходной файл с именем outputName, выровняв по центру."""
        )
        File("temp.txt").delete()

    }

    @Test
    @Tag("20")
    fun alignFileByWidth() {
        alignFileByWidth("input/width_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt",
            """Простая

Во       входном       файле       с       именем       inputName       содержится       некоторый      текст.
Вывести   его  в  выходной  файл  с  именем  outputName,  выровняв  по  левому  и  правому  краю  относительно
самой                                              длинной                                             строки.
Выравнивание   производить,   вставляя  дополнительные  пробелы  между  словами:  равномерно  по  всей  строке

Слова     внутри     строки     отделяются     друг     от     друга     одним     или     более     пробелом.

Следующие                   правила                   должны                  быть                  выполнены:
1)     Каждая     строка     входного    и    выходного    файла    не    должна    заканчиваться    пробелом.
2) Пустые строки или строки из пробелов во входном файле должны превратиться в пустые строки в выходном файле.
3)   Число   строк   в   выходном  файле  должно  быть  равно  числу  строк  во  входном  (в  т.  ч.  пустых).

Равномерность              определяется              следующими             формальными             правилами:
1)  Число  пробелов  между  каждыми  двумя  парами  соседних  слов  не  должно  отличаться  более,  чем  на 1.
2)  Число  пробелов  между  более  левой  парой  соседних  слов  должно  быть  больше или равно числу пробелов
между                более               правой               парой               соседних               слов."""
        )
        File("temp.txt").delete()

    }

    @Test
    @Tag("14")
    fun top20Words() {
        assertEquals(mapOf<String, Int>(), top20Words("input/empty.txt"))
        assertEquals(mapOf(
            "привет" to 4,
            "все" to 3,
            "и" to 3,
            "прямо" to 3,
            "всё" to 2,
            "let" to 2,
            "us" to 2,
            "write" to 2,
            "some" to 2,
            "digits" to 2
        ), top20Words("input/top20.txt").filter { it.value > 1 })
        assertEquals(
            mapOf(
                "и" to 1106,
                "в" to 674,
                "не" to 411,
                "он" to 306,
                "на" to 290,
                "я" to 261,
                "с" to 261,
                "как" to 211,
                "но" to 210,
                "что" to 187,
                "все" to 131,
                "к" to 130,
                "она" to 126,
                "его" to 109,
                "за" to 105,
                "то" to 104,
                "а" to 98,
                "ее" to 95,
                "мне" to 95,
                "уж" to 95,
                "ей" to 95
            ), top20Words("input/onegin.txt")
        )
    }

    @Test
    @Tag("14")
    fun transliterate() {
        transliterate(
            "input/trans_in1.txt",
            mapOf('a' to ""),
            "temp.txt"
        )
        assertFileContent("temp.txt", "Zzdrавствуy,\nmyyr!!!")
        File("temp.txt").delete()

        transliterate(
            "input/trans_in1.txt",
            mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!"),
            "temp.txt"
        )
        assertFileContent("temp.txt", "Zzdrавствуy,\nmyyr!!!")
        File("temp.txt").delete()
    }

    @Test
    @Tag("12")
    fun chooseLongestChaoticWord() {
        chooseLongestChaoticWord("input/chaotic_in1.txt", "temp.txt")
        assertFileContent("temp.txt", "Карминовый, Некрасивый")
        File("temp.txt").delete()
    }


    private fun checkHtmlSimpleExample() {
        val result = File("temp.html").readText().replace(Regex("[\\s\\n\\t]"), "")
        val expected =
            """
                    <html>
                        <body>
                            <p>
                                Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
                                Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
                            </p>
                            <p>
                                Suspendisse <s>et elit in enim tempus iaculis</s>.
                            </p>
                        </body>
                    </html>
                    """.trimIndent().replace(Regex("[\\s\\n\\t]"), "")
        assertEquals(expected, result)

        File("temp.html").delete()
    }

    @Test
    fun testHtmlSimple() {
        val data ="Q.\\n%XW&-\$j\\nf*g*:**s(|Fk**!wp-U09\\nL~~Zz\$~~R^.*+T**qk**g**\\\"**4c?ymcz)OdN9~~**[R**K~~7}\\tJWH\$*%%aP~~HX**p?**~~8b** **w**a~~\$H*b[*d9sR~~mK**3\\n;i[D3K~~asFEa~~n\\n}X*:O**v**6S^z0a6*\\tH9Jnfc/g%dL**bqA-o *\\t{\\tlyQwLtu~~(g]yY26~~e*.bs+obx^Zw-[R~~zC~~o[xIqHp2~~ i1 `uf*}Po7'W)W*2 ~~q@S*h^K^p~~3p~~G7%~~ ~~^~~xNH([~~sR9\$X~~\\\"~~+~~@3~~L]\\\\*b~~wBGJC{~~-~~_#L7Y~~xie*\$*E|I-{~~?Z~~9pBBI+V*N*Px|**gE`\\n%-\\\\\\tz\\nB\\\"MmUDAc2-):@Qk\\ta:8n0l*gfj3r\\tXOb*KP,e\\n`\\nsDWo{M\\n+{j=v^mvL& -~~*6:b3d*!g** *=#W*cH&**CQxc51_#**V**~~;nqLwskES** S**q**,M!q.cCn**M{[_|*B sY3g`FIj~~q\\t**RrHd**P~~kj6* *^Ab**.:Y60.SH0r~~:~~wLM&~~V~~ `K[G|\\n[**v**R4%DDB^\$`hs3bzk.~~JsHYg~~a**c~~^,tm~~**JI~~z}v4W~~N**H\"eQ;ZOzn80e&*,*YlI/I.*slTqe^\ny{9qAT-\$m}i}3\njOoH:'\nD_*D5**-M~~OfhSWRB;\"~~VFHF\"Rk\$aCTKzB~~h]z1=HX.9PM~~4Xw=\$ntxV/z:{_\\=[(f[95XPrRn+zrT~~w_~~ +lVLNfZ^gaR**NM8\$JNnfR1s\\8*~~]x!QB(%F^?O**]xMQD+H2Xyo^0d7?ll0XLC57K![O^1c#r**w\$Y:dH~~vSN8u\"-G@=r-1\\53AL!`d_TyjO`Zah5m{gC0.'X\\nUM}aEZQT\\n**DEq';OVA1*~~GcZ|~~B#\\\\Z*Y;**8d~~d%1U* * **\\\"1}5r**6**ie* *Ar*:ge0^#*m*+#G};D*Uu6A=\\t**{0~~2keA**\\tu~~Cq*%F*o)~~PD*O*)gRk8**P!~~3*p*'~~F~~**\\n**),n~~&I*%G~~S&s/o;**gH[dk!&**~~d*y~~?u**k**W/v9**&r*?5*@06\\\"fB*'7* **wPZ~~ ~~\$~~ro1OH*#J-Rpro0N*=d*^sO*Od!*&*GIon~~T*v* *iJQ*O** **~~3`* *q~~}*/**W**7p*_+Z~~;y#*v**/**m* **xX!*D74wp*M~~Gd*0f*K* *p~~*uuFa~~qe\\\\@:FR~~G\\\" *,~~+oa~~\\\\*Uv*-Bl}**nO)JW, \\n\\nFiCi7\\nmAtjY** *~~mf0W.~~bZy#*9~~A2~~H**a+SPDY\\nklt|wFG\\nW\\n@3huUet1*5}N{pl\\na/^/rAys8]EW*S2;ygH{\\\"\\\"nV/dJp:'p/y|eDtKf-Osq(S+YsR+PJ]Oc7Ia=-:F\\nv\\nC(**~~Nk*2*@:m}Z:8*d!)yP* *f(* * *4~~.**h,\\\\~~* ** **a*MS]*\\n* *y**+**52*c\\\\^** */*Xe#**J**=**~~**6~~ ~~ykE+~~V~~*\\\"L1/~~ry~~@_~~X1yf\$z~~*%*Q!&n* *\$Dd!ukC\\tEM{S7 9@~~/R93~~VL;-*~~-\\n[M?E~~4*D1rL{]4* **`QlxN#R5* **c\\\\i?L** * **H*Q&)*\\\\9:4*DsO^Uf*p\\t'\\\"e52**_Z~~#l-@**.\\n**~~UX3*3q*g*-**bn0iQNX_~~)~~hP(h`E** **n\\n:#N**_**'mVN;y~~ ~~ ~~wsl#~~,~~ ~~@~~ ~~Cgdj**]Y2fv/bdh**\\\\1** **_|(|_r8**h**:** **=(~~)~~eNJ+**93**\\\\** *\$C\\n)?*Js** ~~}TP~~Z0~~[uRLd_?O~~Flf~~ ~~Gr/**rw**Q\\n@**hjF~~;9d~~\\t_k;b;mM}tUJ\$q* *q**q\\n**7*ZOZ~~s**l](T*op*s\\\"*4GdiFYuzV*m^79\\tu^}& ns:5K*k*c**2K~~d**;cm2*&\\\",T6|*Tx3f) ic**dq:r6/**RX**w}]vhig@l_uC,qs\\n64\\njL7VpbC=*H*}-w\\njW,~~,9* 5\\\"** **{jr_*~~\$~~\\n``B~~**gz\\nBQ;u.*~~ ~~\\\"%2*a?**@~~Z**4*Fkzc*LYyssTGn**!**)SViT\\\\*R\\\"JkH * *^fm|*c*u1aj*aX*@*T2EA*+9#*o\\\"**2*yZ8*/%^~~-3Dz**}* * **~~j**TWVp[**b/+**mgMw*o*5**LJAW**m**s'N0C**w\$**_8`+:Sv*C** **76}**r**q*ilnqG3R~~Di\\n%u|E|zIY\\n\\nfJt**F-**(lx\\nO#{#R\\n+|rj|'oSeKT*A~~E64DKJ((Ix=\\\\};wQC~~?-~~axqaa\\\\'m\\t:F5**4**\\n\\\\mYK~~M.bA**ambZI^[=,**0R%Tu9Le\\n]OD**An\\t,8~~\\\\o~~}Er 4nN03fI'.4\$U@a0lD,e&%[]wFoz:lK =b&c[uU+9/b?0=X\\\\'|9X-exO3fntCoSJw@;=y~~{IB~~^**~~\$H~~VR8 **Ie%C\\\"HRh@tO[2**VyCM\$*=VZ&\\nZ9E4k}gp9?`V9jh7|;*tNFuALzPGEd\$\\\"|6**FR0**pm,SM5bB]FDnae1&#xrFrgT#7G&6Y\\nJipGr*|}Y(n{zbR1)*gB6m5]z6Yh,#btuug\\nc_VX-xoq{iuSp9ea;|!0O+7')Zb =JqL7)%GdxZ0-)\\\"/Mv:/#z#,(cx,q:~~u7Jfzj0J cd&WC|FC-:fv@:+wF|a\\tF&tZe3@Jm~~Xt9({\\tD:D,ZBM)A&h*&^kN\\\\QtB6S^\\n\\nNlr]36o.sQ*bNIE*xm**R]xo0- Fv_y.#w4(IrPDcaBt=8,P80w+&~~9Q'AQeyvJ*4OJ*vN&}aOq({I+}(5z4aT\\t?`Kr`y]AvSgT~~:R_Q}U';\\\\E:w,M,eaJo**X)d!va'iHZ'8{ vX`Aq1Q\\n|6\$\$SD\\nuTMZNz\\nk-U.0vH'*~~d~~`;\$*zMdY\\n^}wag\\n0Y:k\\n\\tv6\$f* **Tz[NQbp~~D\\nGoz/bx~~O~~mk)(azu~~i?t'[\\t[t~~z~~Z**\$qm{`*2Ge**@&NA-**!A*\\\\Mb**`}u|hdZz~~tEC~~|~~ ~~}Z;B24**IT)-/~~9&~~C7`~~ ~~'~~Q~~Jq+A/Z:vNv*Td**YT~~*l*JMs^~~7F~~P*)am\\njLl2zQ*gH* *1gn7060IhC23\\\\;h5d%`[E`z9C~~#&q**m\\nE=l{FsG\\nm6\$Fq0=\\n\\n\\n\\n|#\\\"\\n*,\\nV*NYh(**pN'~~OM/*ib0jQ*s]h\$*f* * *3~~1K#n~~#@`4](=jP*:xj*5W?(eUliV*\$.*NO{~~Gm~~|*gz*UV~~e** *'t**g{\\t?ZZ6**h~~_k32B**4F**~~LI\$tjj\\\"**X\\t**B-%j9aBR{~~ ~~K3+U** **yRv/\$\\n]m~~**}8**V%-&{~~d%K*c*l*e**~~*_*-\\tDF%\$*T\$*v*Q#*78\\t]~~sq**)\\nC\\np\$ "
        val testFile = File("input/md_simple.md")
        testFile.writeText(data)
        markdownToHtmlSimple("input/md_simple.md", "new_sample.html")
        testFile.delete()
        val actualFile = File("new_sample.html")
        val actual = actualFile.readText().replace(Regex("[\\s\\n\\t]"), "")
        actualFile.delete()
        val expected ="<html><body><p>Q.%XW&-\$jf<i>g</i>:<b>s(|Fk</b>!wp-U09L<s>Zz\$</s>R^.<i>+T<b>qk</b>g<b>\\\"</b>4c?ymcz)OdN9<s><b>[R</b>K</s>7}JWH\$</i>%%aP<s>HX<b>p?</b></s>8b<b></b>w<b>a<s>\$H<i>b[</i>d9sR</s>mK</b>3;i[D3K<s>asFEa</s>n}X<i>:O<b>v</b>6S^z0a6</i>H9Jnfc/g%dL<b>bqA-o<i>{lyQwLtu<s>(g]yY26</s>e</i>.bs+obx^Zw-[R<s>zC</s>o[xIqHp2<s>i1`uf<i>}Po7'W)W</i>2</s>q@S<i>h^K^p<s>3p</s>G7%<s></s>^<s>xNH([</s>sR9\$X<s>\\\"</s>+<s>@3</s>L]\\\\</i>b<s>wBGJC{</s>-<s>_#L7Y</s>xie<i>\$</i>E|I-{<s>?Z</s>9pBBI+V<i>N</i>Px|</b>gE`%-\\\\zB\\\"MmUDAc2-):@Qka:8n0l<i>gfj3rXOb</i>KP,e`sDWo{M+{j=v^mvL&-<s><i>6:b3d</i>!g<b><i>=#W</i>cH&</b>CQxc51_#<b>V</b></s>;nqLwskES<b>S</b>q<b>,M!q.cCn</b>M{[_|<i>BsY3g`FIj<s>q<b>RrHd</b>P</s>kj6</i><i>^Ab<b>.:Y60.SH0r<s>:</s>wLM&<s>V</s>`K[G|[</b>v<b>R4%DDB^\$`hs3bzk.<s>JsHYg</s>a</b>c<s>^,tm</s><b>JI<s>z}v4W</s>N</b>H\"eQ;ZOzn80e&</i>,<i>YlI/I.</i>slTqe^y{9qAT-\$m}i}3jOoH:'D_<i>D5<b>-M<s>OfhSWRB;\"</s>VFHF\"Rk\$aCTKzB<s>h]z1=HX.9PM</s>4Xw=\$ntxV/z:{_\\=[(f[95XPrRn+zrT<s>w_</s>+lVLNfZ^gaR</b>NM8\$JNnfR1s\\8</i><s>]x!QB(%F^?O<b>]xMQD+H2Xyo^0d7?ll0XLC57K![O^1c#r</b>w\$Y:dH</s>vSN8u\"-G@=r-1\\53AL!`d_TyjO`Zah5m{gC0.'XUM}aEZQT<b>DEq';OVA1<i><s>GcZ|</s>B#\\\\Z</i>Y;</b>8d<s>d%1U<i></i><b>\\\"1}5r</b>6<b>ie<i></i>Ar<i>:ge0^#</i>m<i>+#G};D</i>Uu6A=</b>{0</s>2keA<b>u<s>Cq<i>%F</i>o)</s>PD<i>O</i>)gRk8</b>P!<s>3<i>p</i>'</s>F<s><b></b>),n</s>&I<i>%G<s>S&s/o;<b>gH[dk!&</b></s>d</i>y<s>?u<b>k</b>W/v9<b>&r<i>?5</i>@06\\\"fB<i>'7</i></b>wPZ</s><s>\$</s>ro1OH<i>#J-Rpro0N</i>=d<i>^sO</i>Od!<i>&</i>GIon<s>T<i>v</i><i>iJQ</i>O<b></b></s>3`<i></i>q<s>}<i>/<b>W</b>7p</i>_+Z</s>;y#<i>v<b>/</b>m</i><b>xX!<i>D74wp</i>M<s>Gd<i>0f</i>K<i></i>p</s><i>uuFa<s>qe\\\\@:FR</s>G\\\"</i>,<s>+oa</s>\\\\<i>Uv</i>-Bl}</b>nO)JW,</p><p>FiCi7mAtjY<b><i><s>mf0W.</s>bZy#</i>9<s>A2</s>H</b>a+SPDYklt|wFGW@3huUet1<i>5}N{pla/^/rAys8]EW</i>S2;ygH{\\\"\\\"nV/dJp:'p/y|eDtKf-Osq(S+YsR+PJ]Oc7Ia=-:FvC(<b><s>Nk<i>2</i>@:m}Z:8<i>d!)yP</i><i>f(</i><i></i>4</s>.</b>h,\\\\<s><i><b></b>a</i>MS]<i></i><i>y<b>+</b>52</i>c\\\\^<b><i>/</i>Xe#</b>J<b>=</b></s><b>6<s></s>ykE+<s>V</s><i>\\\"L1/<s>ry</s>@_<s>X1yf\$z</s></i>%<i>Q!&n</i><i>\$Dd!ukCEM{S79@<s>/R93</s>VL;-</i><s>-[M?E</s>4<i>D1rL{]4</i></b>`QlxN#R5<i><b>c\\\\i?L</b></i><b>H<i>Q&)</i>\\\\9:4<i>DsO^Uf</i>p'\\\"e52</b>_Z<s>#l-@<b>.</b></s>UX3<i>3q</i>g<i>-<b>bn0iQNX_<s>)</s>hP(h`E</b><b>n:#N</b>_<b>'mVN;y<s></s><s>wsl#</s>,<s></s>@<s></s>Cgdj</b>]Y2fv/bdh<b>\\\\1</b><b>_|(|_r8</b>h<b>:</b><b>=(<s>)</s>eNJ+</b>93<b>\\\\</b></i>\$C)?<i>Js<b><s>}TP</s>Z0<s>[uRLd_?O</s>Flf<s></s>Gr/</b>rw<b>Q@</b>hjF<s>;9d</s>_k;b;mM}tUJ\$q</i><i>q<b>q</b>7</i>ZOZ<s>s<b>l](T<i>op</i>s\\\"<i>4GdiFYuzV</i>m^79u^}&ns:5K<i>k</i>c</b>2K</s>d<b>;cm2<i>&\\\",T6|</i>Tx3f)ic</b>dq:r6/<b>RX</b>w}]vhig@l_uC,qs64jL7VpbC=<i>H</i>}-wjW,<s>,9<i>5\\\"<b></b>{jr_</i></s>\$<s>``B</s><b>gzBQ;u.<i><s></s>\\\"%2</i>a?</b>@<s>Z<b>4<i>Fkzc</i>LYyssTGn</b>!<b>)SViT\\\\<i>R\\\"JkH</i><i>^fm|</i>c<i>u1aj</i>aX<i>@</i>T2EA<i>+9#</i>o\\\"</b>2<i>yZ8</i>/%^</s>-3Dz<b>}<i></i></b><s>j<b>TWVp[</b>b/+<b>mgMw<i>o</i>5</b>LJAW<b>m</b>s'N0C<b>w\$</b>_8`+:Sv<i>C<b></b>76}<b>r</b>q</i>ilnqG3R</s>Di%u|E|zIY</p><p>fJt<b>F-</b>(lxO#{#R+|rj|'oSeKT<i>A<s>E64DKJ((Ix=\\\\};wQC</s>?-<s>axqaa\\\\'m:F5<b>4</b>\\\\mYK</s>M.bA<b>ambZI^[=,</b>0R%Tu9Le]OD<b>An,8<s>\\\\o</s>}Er4nN03fI'.4\$U@a0lD,e&%[]wFoz:lK=b&c[uU+9/b?0=X\\\\'|9X-exO3fntCoSJw@;=y<s>{IB</s>^</b><s>\$H</s>VR8<b>Ie%C\\\"HRh@tO[2</b>VyCM\$</i>=VZ&Z9E4k}gp9?`V9jh7|;<i>tNFuALzPGEd\$\\\"|6<b>FR0</b>pm,SM5bB]FDnae1&#xrFrgT#7G&6YJipGr</i>|}Y(n{zbR1)<i>gB6m5]z6Yh,#btuugc_VX-xoq{iuSp9ea;|!0O+7')Zb=JqL7)%GdxZ0-)\\\"/Mv:/#z#,(cx,q:<s>u7Jfzj0Jcd&WC|FC-:fv@:+wF|aF&tZe3@Jm</s>Xt9({D:D,ZBM)A&h</i>&^kN\\\\QtB6S^</p><p>Nlr]36o.sQ<i>bNIE</i>xm<b>R]xo0-Fv_y.#w4(IrPDcaBt=8,P80w+&<s>9Q'AQeyvJ<i>4OJ</i>vN&}aOq({I+}(5z4aT?`Kr`y]AvSgT</s>:R_Q}U';\\\\E:w,M,eaJo</b>X)d!va'iHZ'8{vX`Aq1Q|6\$\$SDuTMZNzk-U.0vH'<i><s>d</s>`;\$</i>zMdY^}wag0Y:kv6\$f<i><b>Tz[NQbp<s>DGoz/bx</s>O<s>mk)(azu</s>i?t'[[t<s>z</s>Z</b>\$qm{`</i>2Ge<b>@&NA-</b>!A<i>\\\\Mb<b>`}u|hdZz<s>tEC</s>|<s></s>}Z;B24</b>IT)-/<s>9&</s>C7`<s></s>'<s>Q</s>Jq+A/Z:vNv</i>Td<b>YT<s><i>l</i>JMs^</s>7F<s>P<i>)amjLl2zQ</i>gH<i></i>1gn7060IhC23\\\\;h5d%`[E`z9C</s>#&q</b>mE=l{FsGm6\$Fq0=</p><p>|#\\\"<i>,V</i>NYh(<b>pN'<s>OM/<i>ib0jQ</i>s]h\$<i>f</i><i></i>3</s>1K#n<s>#@`4](=jP<i>:xj</i>5W?(eUliV<i>\$.</i>NO{</s>Gm<s>|<i>gz</i>UV</s>e</b><i>'t<b>g{?ZZ6</b>h<s>_k32B<b>4F</b></s>LI\$tjj\\\"<b>X</b>B-%j9aBR{<s></s>K3+U<b></b>yRv/\$]m<s><b>}8</b>V%-&{</s>d%K</i>c<i>l</i>e<b><s><i>_</i>-DF%\$<i>T\$</i>v<i>Q#</i>78]</s>sq</b>)Cp</p></body></html> ".replace(Regex("[\\s\\n\\t]"), "")
        assertEquals(expected, actual)
    }

    @Test
    fun testHtmlSimpleS() {
        val data ="|**V^B%\\t?"
        val testFile = File("input/md_simple.md")
        testFile.writeText(data)
        markdownToHtmlSimple("input/md_simple.md", "new_sample.html")
        testFile.delete()
        val actualFile = File("new_sample.html")
        val actual = actualFile.readText().replace(Regex("[\\s\\n\\t]"), "")
        actualFile.delete()
        val expected ="<html><body><p>hgfhgf\\\\t\\\\ ;</p></body></html>"
        assertEquals(expected, actual)
    }

    @Test
    @Tag("22")
    fun markdownToHtmlSimple() {
        markdownToHtmlSimple("input/markdown_simple.md", "temp.html")
        checkHtmlSimpleExample()
    }

    private fun checkHtmlListsExample() {
        val result = File("temp.html").readText().replace(Regex("[\\s\\n\\t]"), "")
        val expected =
            """
                    <html>
                      <body>
                        <p>
                          <ul>
                            <li>Утка по-пекински
                              <ul>
                                <li>Утка</li>
                                <li>Соус</li>
                              </ul>
                            </li>
                            <li>Салат Оливье
                              <ol>
                                <li>Мясо
                                  <ul>
                                    <li>Или колбаса</li>
                                  </ul>
                                </li>
                                <li>Майонез</li>
                                <li>Картофель</li>
                                <li>Что-то там ещё</li>
                              </ol>
                            </li>
                            <li>Помидоры</li>
                            <li>Фрукты
                              <ol>
                                <li>Бананы</li>
                                <li>Яблоки
                                  <ol>
                                    <li>Красные</li>
                                    <li>Зелёные</li>
                                  </ol>
                                </li>
                              </ol>
                            </li>
                          </ul>
                        </p>
                      </body>
                    </html>
                    """.trimIndent().replace(Regex("[\\s\\n\\t]"), "")
        assertEquals(expected, result)

        File("temp.html").delete()
    }

    @Test
    @Tag("23")
    fun markdownToHtmlLists() {
        markdownToHtmlLists("input/markdown_lists.md", "temp.html")
        checkHtmlListsExample()
    }

    @Test
    @Tag("30")
    fun markdownToHtml() {
        markdownToHtml("input/markdown_simple.md", "temp.html")
        checkHtmlSimpleExample()

        markdownToHtml("input/markdown_lists.md", "temp.html")
        checkHtmlListsExample()
    }

    @Test
    @Tag("12")
    fun printMultiplicationProcess() {
        fun test(lhv: Int, rhv: Int, res: String) {
            printMultiplicationProcess(lhv, rhv, "temp.txt")
            assertFileContent("temp.txt", res.trimIndent())
            File("temp.txt").delete()
        }

        test(
            19935,
            111,
            """
                19935
             *    111
             --------
                19935
             + 19935
             +19935
             --------
              2212785
             """
        )

        test(
            12345,
            76,
            """
               12345
             *    76
             -------
               74070
             +86415
             -------
              938220
             """
        )

        test(
            12345,
            6,
            """
              12345
             *    6
             ------
              74070
             ------
              74070
             """
        )

    }

    @Test
    @Tag("25")
    fun printDivisionProcess() {

        fun test(lhv: Int, rhv: Int, res: String) {
            printDivisionProcess(lhv, rhv, "temp.txt")
            assertFileContent("temp.txt", res.trimIndent())
            File("temp.txt").delete()
        }

        test(
            19935,
            22,
            """
              19935 | 22
             -198     906
             ----
                13
                -0
                --
                135
               -132
               ----
                  3
             """
        )

        test(
            2,
            20,
            """
              2 | 20
             -0   0
             --
              2
             """
        )

        test(
            99999,
            1,
            """
              99999 | 1
             -9       99999
             --
              09
              -9
              --
               09
               -9
               --
                09
                -9
                --
                 09
                 -9
                 --
                  0
             """
        )

        File("temp.txt").delete()
    }
}

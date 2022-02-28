CREATE SEQUENCE recommendations_seq;

CREATE TABLE recommendations (
   id                 bigint            NOT NULL DEFAULT nextval('recommendations_seq'),
   diagnostic_id      bigint            NOT NULL REFERENCES diagnostic(id),
   text               character varying,

   CONSTRAINT pk_recommendations PRIMARY KEY (id)
);

INSERT INTO recommendations (diagnostic_id, text)
VALUES
(1, 'Чаще всего такое сочетание показателей бывает у человека, который старается показать себя социально нормативным, «хорошим», как ему это кажется. Обычно такие люди демонстрируют сниженную самокритичность, ведут себя претенциозно, неискренни.\nТак же, подобные результаты может дать пассивный человек, который старается быть неприметным и не стремится к высоким достижениям. Такой человек вряд ли станет хозяином своей судьбы, лидером в коллективе, предпринимателем или борцом за идеи. Скорее, он окунется в мистику, веру в Бога, чем станет решительно менять свою судьбу. Исследования позволяют утверждать, что подобные люди не могут противостоять трудным жизненным обстоятельствам.'),
(1, 'Чаще всего такое сочетание показателей бывает у по-настоящему яркой личности, но обладающей многими «острыми» углами. Если при этом отдельные акцентуации достигли отметки в 22 балла – то могут быть проблемы в общении с другими людьми из-за вашей яркости и бескомпромиссности.'),
(1, 'Ваши результаты свидетельствуют о недостатке энергетических ресурсов психики, необходимых для активной деятельности. Это может дополнительно усиливаться флегматическим или меланхолическим темпераментом.'),
(1, 'Ваши результаты говорят, что вы - личность с мощными жизненными силами. Это может дополнительно усиливаться сангвиническим или холерическим темпераментом.'),
(1, 'Результаты свидетельствуют об отсутствии выразительных реакций на происходящее, что, как правило, приводит к низкой контактности с окружающими.'),
(1, 'Результаты характерны для личности, у которой эмоционально-чувственная жизнь настолько разнообразна, что становится неподвластной разуму. У нее могут возникать коммуникативные проблемы, поскольку эмоции и чувства могут проявляться слишком ярко и неадекватно ситуациям.'),
(1, 'У вас сильно выражена демонстративность и гипертимность. У взрослых людей такая комбинация часто ослабляет различные аморальные проявления. Открытые натуры, которыми большей частью являются носители гипертимной акцентуации, избирают прямые пути. Хитрость, неискренность, притворство не вяжутся с их жизненной установкой.\nДетям, в сравнении со взрослыми людьми, менее свойственно торможение побуждений, в том числе и нечестных, особенно если речь идет об исполнении желаний. Кроме того, у детей иногда наблюдается безудержное стремление хотя бы в чем-то себя проявить – своеобразное детское тщеславие. У взрослых одновременное проявление таких разноплановых свойств почти не встречается.\nТакое сочетание акцентуаций часто наблюдается у журналистов, политиков, эстрадных актеров и других публичных деятелей, для которых важны открытость миру, новым впечатлениям, а также богатство воображения и фантазии.'),
(1, 'У вас сильно выражена гипертимность и педантичность. Поскольку эти черты различны по своей природе, они проявляются не как компенсирующие друг друга, а как взаимодополняющие и вполне совместимые. Так, гипертиму ничто не помешает быть хорошим работником, если он обладает и педантическими чертами. При этом гипертимные черты, совместно с педантичными, способствуют продвижению в жизнь заранее подготовленных и добросовестно спланированных дел.\nВ целом гипертимная акцентуация оберегает педантическую личность от невроза навязчивого страха, но не от фобий, которые появляются временами, хотя серьезно на образе жизни человека не отражаются – навязчивый страх не носит острого характера и не охватывает личность в целом.'),
(1, 'У вас сильно выражена педантичность и дистимность. Возможно, сами по себе эти акцентуированные черты не были бы столь серьезны, если бы одна не усиливалась за счет другой. Обычно сочетание педантичности и дистимии отражается на всем образе жизни. Дистимический темперамент, которому свойственны недостаток энергии и активности, способствует пессимистическому восприятию жизни, а склонность к навязчивому раздумыванию, характерная для педантов, приводит к депрессиям.\nУ данной комбинации черт есть и положительная сторона – для педантичных и одновременно дистимных людей характерны такие качества как добросовестность, вдумчивость, серьезное отношение к жизни.'),
(1, 'У вас сильная педантичность в сочетании с тревожностью. Одним из важнейших признаков каждой из этих акцентуаций является страх. Поэтому если оба вида акцентуации наблюдаются у одного человека, возможен суммирующий эффект, который может привести к неврозу. Например, заики обычно являются либо педантами, либо обладают тревожной акцентуацией, но чаще всего у них сочетается и то и другое. Подобные проблемы возникают еще в детстве. У ребенка тревожность часто связана с напряжением, при котором возникают нарушения со стороны артикуляции, но, если ребенок к тому же и педантичен, он начинает усиленно следить за речью, что еще больше усугубляет нарушения.\nУ взрослых людей педантичность может служить одним из способов преодоления проблем, связанных с тревожностью. Стремление контролировать ситуацию помогает справиться с беспомощностью путем конкретных действий. Таких, например, как «дотошное» прояснение ситуации и условий достижения успеха в ней, тщательная подготовка к ситуации, соблюдение этих условий (с учетом времени, места событий и необходимых требований). Подобное поведение можно наблюдать со стороны студентов, которые вовремя приходят на занятия, редко (практически никогда) не пропускают занятия, старательно готовятся к зачетам и экзаменам и максимально подробно выясняют вопрос о том, что и когда они должны предпринять, чтобы справиться с поставленными преподавателем задачами.'),
(1, 'Одновременно высокие показатели гипертимности и дистимности указывают на ситуацию, в которой (добровольно или вынужденно) человек отдает все силы для достижения поставленной цели. При этом у него могут проявляться признаки переутомления, сомнения в оправданности поставленных целей и своей способности признавать поражение.\nЕсли у человека недостаточно ресурсов для того, чтобы поставить и решить для себя эти вопросы (повышение по шкале застревания, снижение по шкале тревоги), существует риск «ухода в болезнь».\nЕсли у человека есть ресурсы для гибкого и адекватного реагирования (высокие показатели по шкалам циклотимности, эмотивности, педантичности), он может совершить в этой ситуации выбор, основанный на волевом сознательном усилии (продолжать добиваться своей цели, используя новые средства), или признать свой первоначальный выбор ошибочным.'),
(1, 'Высокая гипертимность при низкой дистимности часто встречается у людей, активно включенных в общение, а также у людей, отдающих много сил своему хобби.\nПри интерпретации такого сочетания возможны три варианта:\n- человек стремится произвести впечатление, подчеркивает, что ему «все по плечу» (этот вариант часто встречается в ситуациях тестирования при приеме на работу);\n- человек воспринимает ситуацию как благоприятную, позволяющую добиться многого, не затрачивая значительных усилий, при этом его потенциальные возможности реализуются не в полной мере;\n- человек не замечает усталости, игнорирует ее, проверяет себя на прочность (проявляется эффект «адреналинового допинга»).'),
(1, 'Низкая гипертимность при высокой дистимности указывает на ситуацию болезни или спада. Такое состояние может пугать человека, восприниматься как «маленькая смерть», актуализировать обвинения или самообвинения.\nС другой стороны, это сочетание может соответствовать поведению, в котором демонстрируется исчерпанность собственных ресурсов и востребуются ресурсы окружающих. Для первичной оценки реальной тяжести состояния можно рассмотреть шкалы циклотимности и застревания, для более подробного анализа необходимо привлечь показатели по шкалам эмотивности и тревожности.'),
(1, 'Одновременно низкие показатели по шкалам гипертимности и дистимности описывают состояние «блаженного ничегонеделания», полной расслабленности. Но при этом у вас достаточно ресурсов и вы готовы к действию, если это будет нужно.'),
(1, 'Одновременно низкие показатели по шкалам гипертимности и дистимности описывают состояние «блаженного ничегонеделания», полной расслабленности. При этом есть признаки депрессивного состояния, характеризующегося потерей смысла и отсутствием побуждений.'),
(1, 'У вас высокие показатели гипертимности и циклотимности. Это говорит об активности в поиске чего-то, но степень осознанности такой позиции, ее рефлексивность, а также область, в которой ведется этот поиск, невозможно описать при помощи этой диагностики.'),
(1, 'У вас высокая гипертимность при низкой циклотимности. Обычно такое бывает, когда человек убежден, что путь, который он выбрал для достижения своей цели, является правильным. При этом сочетании в поведении человека может проявляться настойчивость, целеустремленность, но одновременно снижается его способность выйти за рамки ситуации, увидеть и оперативно отреагировать на ее изменения.'),
(1, 'Низкая гипертимность при высокой циклотимности может свидетельствовать:\n- о том, что в состоянии человека произошли недавние перемены, связанные с внешними неблагоприятными событиями (переутомление и/или болезнь, увольнение с работы, разрыв с любимым человеком), и он пытается приспособиться к новой ситуации, несмотря на субъективную очевидность того, что эти внешние обстоятельства доминируют, не позволяют реализовать свободу воли и выбора;\n- о гибкости реагирования в неблагоприятной ситуации, осознании и преодолении собственных негативных стереотипических реакций.'),
(1, 'Одновременно низкие показатели гипертимности и циклотимности могут указывать на внутреннее принятие своей слабости, в ряде случаев – на депрессивное состояние. Присутствует представление о протяженности этого состояния во времени, «монотонности переживания»: то, что было сегодня, будет завтра и всегда. Субъективно человек сопротивляется самой идее возможных перемен (они нежелательны или невозможны). Чем более тяжело он оценивает свое состояние, тем более устойчивым оно является.'),
(1, 'У вас высокие показатели дистимности и циклотимности. Они свидетельствуют о неудовлетворенности человека своим нынешним состоянием. Вне зависимости от тяжести соматического нарушения или эмоционального неблагополучия, которые «ответственны» за повышение дистимности, человек воспринимает свое состояние как временное, старается переломить ситуацию.\nМожно предположить, что проблема, лежащая в основе данного сочетания, недавнего происхождения или (что более вероятно) недавно произошло переосмысление кризисной ситуации. В том случае, если человек принимает на себя ответственность за то, что происходит в его жизни, подобное сочетание может указывать на вероятное изменение ролевых стереотипов, привлечение новых ресурсов.\nВ любом случае, ситуация, обусловленная подобным сочетанием шкал, свидетельствует о наличии внутренней активности, которая лишь в незначительной степени выражается вовне и обладает потенциалом изменения, развития.'),
(1, 'Высокая дистимность при низкой циклотимности может указывать на то, что кризисная ситуация воспринимается как неизбежная, давящая. Человек может испытывать апатию, смирение перед соматическим заболеванием, проявляется накопление усталости. Присутствует ощущение обреченности: «сейчас уже поздно поворачивать назад», «ничего не поделаешь», «не изменишь».\nОжидается внешнее разрешение ситуации: кто-то должен прийти и сказать, что «розы вырастут сами, поезжай на бал», в то время как человек продолжает осуществлять ранее заданную себе «программу». Все выборы уже сделаны, осталось устоять, продержаться (в таком случае будут присутствовать высокие показатели по шкалам эмотивности и гипертимности).'),
(1, 'Низкая дистимность при высокой циклотимности указывает, прежде всего, на высокую поисковую, ориентировочную активность. Человек с подобным сочетанием показателей может иметь широкий круг знакомых, множество хобби, а также заниматься поиском работы. В поведении весьма вероятно проявятся оптимистичность (вплоть до значительной самонадеянности) и «жадность» до всего нового.\nТакой профиль является нормальным для людей молодых, не создавших собственной семьи и не прошедших этап профессионального самоопределения. Для более взрослых людей это же сочетание может указывать на низкую удовлетворенность отношениями, которые сложились в жизни. Возможно, человек стремится поменять работу и на своем нынешнем месте работает «спустя рукава».\nПри приеме на работу важно учитывать, что такое сочетание шкал может указывать на недостаточную искренность и недостоверность результатов (демонстрируемой ценностью является энтузиазм, легкость на подъем).'),
(1, 'У вас низкие показатели дистимности и циклотимности. Они могут свидетельствовать о том, что человек не реализует свой потенциал, возможно, идет по линии наименьшего сопротивления. Для того, чтобы объяснить это сочетание, необходим анализ других показателей.'),
(1, 'У вас высокие показатели циклотимности и экзальтированности. Они свидетельствуют о лабильности (подвижности) нервной системы. Соответствующие черты взаимно усиливают друг друга.'),
(1, 'Низкая циклотимностъ при высокой экзальтированности может указывать на стремление романтизировать отношения между людьми, а также на перфекционизм. В то же время человеку может быть свойственна ребячливость, непосредственность. В своей оценке происходящего такой человек может использовать полярные характеристики (выражать отвращение, возмущение или восторженность).'),
(1, 'Одновременно низкие показатели по шкале циклотимности и экзальтированности, подчеркивают интровертированность человека, сдержанность, внешнюю пассивность. В ряде случаев отмечался повышенный самоконтроль, стремление произвести впечатление более зрелого, рассудительного человека.\nТакое сочетание может характеризовать стояние апатии, но может проявиться и при положительном фоне настроения – как «блаженное ничегонеделание».'),
(1, 'У вас высокие показатели эмотивности и экзальтированности. Они взаимно усиливают позитивные аспекты друг друга. Чувства человека сильны, глубоки и непосредственны. В поведении может проявляться спонтанность, искренность, глубокая заинтересованность в другом человеке, некоторая ребячливость. Интересы в профессиональной сфере выражены менее ярко, особенно если работа не связана непосредственно с общением.'),
(1, 'Высокая эмотивность при низкой экзальтированности свидетельствует о стремлении поддерживать устойчивый круг общения, избирательности в знакомствах, о наличии устойчивых этических ценностей и ориентации на взаимопонимание. Из-за склонности к идеализации других людей возможны затруднения при установлении доверительных отношений.'),
(1, 'Низкая эмотивность при высокой экзальтированности может проявляться в стремлении жить настоящим моментом, постичь всю полноту, красоту мира «здесь и сейчас». Такой человек отличается спонтанностью поведения, хорошей интуицией, способен проявлять сочувствие. При этом у него слабо простроена система приоритетов, в своих мнениях и оценках он, как правило, проявляет конформизм. Эмоциональные обязательства рассматриваются человеком как иррациональные, ограничивающие.'),
(1, 'Одновременно низкие показатели эмотивности и экзальтированности могут соответствовать ряду различных проявлений:\n- в поведении человека проявляется холодность, дистанцированность, отчужденность;\n- поведение человека оценивается окружающими резко негативно (ему могут приписываться склонность к манипуляции, неискренность и т.п.);\n- человек проявляет интерес в большей степени к профессиональной сфере, чем к сфере отношений (его ценности связаны с профессиональным или карьерным ростом, уровень притязаний завышен);\n- в прошлом человека есть опыт разрушительных близких отношений, потери доверия; собственный травматический опыт в области интимно-личностного общения отвергается, нынешняя модель поведения рассматривается как более мудрая и успешная;\n- при самооценке человек может подчеркивать сочетание этих черт в том случае, если у него есть склонность к физической агрессии или отсутствует навык социально-приемлемого выражения негативных переживаний.'),
(1, 'У вас высокие показатели гипертимности и тревожности и эмотивности. Они могут указывать на предельное напряжение сил в ситуации опасности. Тревога личностно окрашена, а ее повод чаще всего объективен и значим. Человек с подобным сочетанием черт может достичь результатов, которые со стороны выглядят как сверхчеловеческие. Например, мать переворачивает машину, под которую попал ее ребенок.'),
(1, 'У вас высокие показатели гипертимности и тревожности и низкие - эмотивности. Они могут указывать на предельное напряжение сил в ситуации опасности. При этом угрожающий фактор, скорее всего, ситуативный и внешний (например, надвигающаяся сессия, ревизия).'),
(1, 'Ваши показатели говорят о наличии страха иррациональной природы, навязчивости. Рекомендуется консультация специалиста.'),
(1, 'Высокая гипертимность при низкой тревожности позволяет выдвинуть несколько гипотез:\n- человек занимается любимым делом (хобби) и не испытывает тревоги по поводу своего успеха или неуспеха. Эта деятельность такого рода, которая не требует высокой ответственности;\n- человек занимается любимым делом, он переживает подъем, это дело придает ему уверенность в себе, он оценивает себя как высоко успешного; прогнозы на будущее оптимистичны, при этом игнорируется возможность неблагоприятного развития событий;\n- человек занимается нелюбимым делом (или чем-то, что ему безразлично), но его стимулируют формальные требования или некая внешняя цель (накопить деньги для крупной покупки);\n- человек «прячется» в деятельности от проблем, которые не готов решать (высокая дистимность и застревание, низкая циклотимность и эмотивность).\nПримером, иллюстрирующим последнюю из гипотез, является человек, дни и ночи проводящий на работе, которая не приносит ему удовлетворения. Близкие часто обвиняют такого человека в холодности и безразличии, в ответ на это он заявляет, что «все делает для семьи».'),
(1, 'Ваши показатели говорят о какой-то проблемой ситуации, в которой не хватает сил для достижения поставленной цели. При этом вы можете выйти из неё через поддержку и сочувствие других людей (при высокой демнстративности) или за счёт исключительной собранности и точности, эвристичности, нестандартности подхода (в этом случае будет повышен показатель по шкале педантичности).'),
(1, 'Ваши показатели говорят о какой-то проблемой ситуации, из-за которой существует большой риск развития депрессии.'),
(1, 'Ваши показатели говорят о какой-то проблемой ситуации, в которой ваша психика чувствует себя беспомощной. Такое бывает, например, из-за болезни.'),
(1, 'Ваши показатели говорят о какой-то проблемой ситуации, в которой ваша психика чувствует себя беспомощной. При этом эта роль ей выгодна и она её проживает с упоением.'),
(1, 'Одновременно низкие показатели гипертимности и тревожности, указывают на то, что ваша психика находится в своеобразном периоде релаксации, своеобразном «отпуске», который вы предоставили себе в период между окончанием одного этапа и началом другого.'),
(1, ''),
(1, ''),
(1, ''),
(1, ''),
(1, ''),
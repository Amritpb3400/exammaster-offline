package com.example.data.local

import com.example.data.local.entity.QuestionEntity
import com.example.data.local.entity.StudyNoteEntity
import com.example.data.local.entity.TimetableSlotEntity

object InitialData {
    val initialQuestions = listOf(
        // UPSC - Indian Polity
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Polity",
            topic = "Fundamental Rights",
            difficulty = "Medium",
            questionText = "Which Article of the Indian Constitution is referred to as the 'Heart and Soul of the Constitution' by Dr. B.R. Ambedkar?",
            optionA = "Article 14 (Right to Equality)",
            optionB = "Article 19 (Right to Freedom)",
            optionC = "Article 21 (Right to Life)",
            optionD = "Article 32 (Right to Constitutional Remedies)",
            correctOption = 3,
            explanation = "Dr. B.R. Ambedkar termed Article 32 as the 'Heart and Soul' because it guarantees the right to move the Supreme Court by appropriate proceedings for the enforcement of Fundamental Rights via writs (Habeas Corpus, Mandamus, Prohibition, Quo-Warranto, and Certiorari)."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Polity",
            topic = "Preamble & Structure",
            difficulty = "Easy",
            questionText = "By which Constitutional Amendment were the words 'Socialist', 'Secular', and 'Integrity' added to the Preamble of the Indian Constitution?",
            optionA = "42nd Constitutional Amendment Act, 1976",
            optionB = "44th Constitutional Amendment Act, 1978",
            optionC = "52nd Constitutional Amendment Act, 1985",
            optionD = "73rd Constitutional Amendment Act, 1992",
            correctOption = 0,
            explanation = "The 42nd Amendment Act of 1976 (also known as the Mini Constitution) added the words 'Socialist', 'Secular', and 'Integrity' to the Preamble under the Indira Gandhi administration."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "History",
            topic = "Modern Indian History",
            difficulty = "Medium",
            questionText = "Who presided over the historic Lahore Session of the Indian National Congress in 1929, where the 'Purna Swaraj' resolution was adopted?",
            optionA = "Mahatma Gandhi",
            optionB = "Jawaharlal Nehru",
            optionC = "Subhas Chandra Bose",
            optionD = "Sardar Vallabhbhai Patel",
            correctOption = 1,
            explanation = "Pt. Jawaharlal Nehru presided over the 1929 Lahore Session where the resolution for 'Purna Swaraj' (Complete Independence) was declared, and 26 January 1930 was declared as Independence Day."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Geography",
            topic = "Physical Geography of India",
            difficulty = "Medium",
            questionText = "Which Indian state has the longest coastline in mainland India?",
            optionA = "Maharashtra",
            optionB = "Tamil Nadu",
            optionC = "Gujarat",
            optionD = "Andhra Pradesh",
            correctOption = 2,
            explanation = "Gujarat has the longest mainland coastline in India, extending approximately 1,600 km along the Arabian Sea, featuring the Gulf of Kutch and Gulf of Khambhat."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Economy",
            topic = "Monetary Policy",
            difficulty = "Hard",
            questionText = "When the Reserve Bank of India (RBI) decreases the Cash Reserve Ratio (CRR), what is the immediate effect on commercial banks?",
            optionA = "Lending capacity of commercial banks decreases",
            optionB = "Lending capacity of commercial banks increases",
            optionC = "Interest rates on fixed deposits necessarily double",
            optionD = "Government fiscal deficit decreases immediately",
            correctOption = 1,
            explanation = "A decrease in CRR frees up reserves that banks are legally required to hold with the RBI. This injects liquidity into the banking system, directly increasing their lending capacity."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Environment & Ecology",
            topic = "Biodiversity Hotspots",
            difficulty = "Medium",
            questionText = "Which of the following is designated as one of the four global biodiversity hotspots found in India?",
            optionA = "Thar Desert",
            optionB = "Western Ghats",
            optionC = "Chota Nagpur Plateau",
            optionD = "Aravalli Range",
            correctOption = 1,
            explanation = "The Western Ghats (along with Eastern Himalayas, Indo-Burma, and Sundaland) is recognized as a global biodiversity hotspot due to its extraordinary species richness and high degree of endemism."
        ),

        // SSC CGL - Quantitative Aptitude
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Quantitative Aptitude",
            topic = "Percentages & Profit Loss",
            difficulty = "Easy",
            questionText = "An article is sold for ₹840 at a gain of 20%. What was the cost price (CP) of the article?",
            optionA = "₹680",
            optionB = "₹700",
            optionC = "₹720",
            optionD = "₹750",
            correctOption = 1,
            explanation = "Selling Price = CP × (1 + Profit%/100). Therefore, ₹840 = CP × 1.20 => CP = 840 / 1.2 = ₹700."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Quantitative Aptitude",
            topic = "Time and Work",
            difficulty = "Medium",
            questionText = "A can complete a piece of work in 12 days and B can complete it in 24 days. Working together, in how many days can they complete the work?",
            optionA = "6 days",
            optionB = "8 days",
            optionC = "9 days",
            optionD = "10 days",
            correctOption = 1,
            explanation = "1 day work of A = 1/12, B = 1/24. Combined 1 day work = 1/12 + 1/24 = (2 + 1)/24 = 3/24 = 1/8. Hence, together they take 8 days."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Quantitative Aptitude",
            topic = "Geometry & Mensuration",
            difficulty = "Hard",
            questionText = "The radius of a circular cylinder is increased by 20% and its height is decreased by 10%. What is the percentage change in its volume?",
            optionA = "Increase of 29.6%",
            optionB = "Increase of 10%",
            optionC = "Increase of 18.8%",
            optionD = "Decrease of 5.4%",
            correctOption = 0,
            explanation = "Volume V = π r² h. New r = 1.2r, New h = 0.9h. New V = π (1.2r)² (0.9h) = 1.44 × 0.9 × π r² h = 1.296 V. Percentage change = +29.6%."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Reasoning Ability",
            topic = "Series & Analogy",
            difficulty = "Easy",
            questionText = "Find the missing number in the sequence: 4, 9, 25, 49, 121, ?",
            optionA = "144",
            optionB = "169",
            optionC = "196",
            optionD = "225",
            correctOption = 1,
            explanation = "These are squares of consecutive prime numbers: 2² = 4, 3² = 9, 5² = 25, 7² = 49, 11² = 121. The next prime number is 13, and 13² = 169."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "English Language",
            topic = "Idioms & Phrases",
            difficulty = "Easy",
            questionText = "What is the meaning of the idiom 'To burn the midnight oil'?",
            optionA = "To waste energy carelessly",
            optionB = "To study or work late into the night",
            optionC = "To cause an accidental fire",
            optionD = "To start a new business venture",
            correctOption = 1,
            explanation = "'To burn the midnight oil' means to work diligently or study late into the night, historically referring to working by the light of an oil lamp."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "General Awareness",
            topic = "Indian Constitution",
            difficulty = "Easy",
            questionText = "Who is known as the guardian of the Public Purse in India?",
            optionA = "Finance Minister of India",
            optionB = "Comptroller and Auditor General (CAG)",
            optionC = "Prime Minister of India",
            optionD = "Chairman of the Public Accounts Committee",
            correctOption = 1,
            explanation = "The Comptroller and Auditor General of India (CAG), under Article 148, is hailed as the guardian of the public purse and audits all expenditure from the Consolidated Fund of India."
        ),

        // Banking / IBPS
        QuestionEntity(
            examCategory = "Banking",
            subject = "Banking Awareness",
            topic = "Payment Systems & Regulatory",
            difficulty = "Medium",
            questionText = "What does the abbreviation 'RTGS' stand for in Indian Banking System?",
            optionA = "Real Time Gross Settlement",
            optionB = "Rapid Transfer Guaranteed System",
            optionC = "Regional Treasury Growth Scheme",
            optionD = "Real Time General Securities",
            correctOption = 0,
            explanation = "RTGS stands for Real Time Gross Settlement. It refers to a funds transfer system where transfer of money or securities takes place continuously on a real-time, order-by-order basis without netting."
        ),
        QuestionEntity(
            examCategory = "Banking",
            subject = "Banking Awareness",
            topic = "RBI & Rates",
            difficulty = "Medium",
            questionText = "What is the rate at which the Reserve Bank of India lends money to commercial banks against government securities for short-term requirements?",
            optionA = "Reverse Repo Rate",
            optionB = "Repo Rate",
            optionC = "Bank Rate",
            optionD = "Marginal Standing Facility",
            correctOption = 1,
            explanation = "Repo Rate (Repurchase Option Rate) is the benchmark interest rate at which RBI lends money to commercial banks against pledged government securities."
        ),
        QuestionEntity(
            examCategory = "Banking",
            subject = "Reasoning Ability",
            topic = "Syllogism",
            difficulty = "Medium",
            questionText = "Statements: All pens are books. Some books are pencils. Conclusion I: Some pens are pencils. Conclusion II: Some books are pens.",
            optionA = "Only Conclusion I follows",
            optionB = "Only Conclusion II follows",
            optionC = "Both I and II follow",
            optionD = "Neither follows",
            correctOption = 1,
            explanation = "Since 'All pens are books', it directly converts to 'Some books are pens' (Conclusion II is valid). But 'Some books are pencils' does not guarantee overlap with pens, so I does not necessarily follow."
        ),
        QuestionEntity(
            examCategory = "Banking",
            subject = "Quantitative Aptitude",
            topic = "Simple and Compound Interest",
            difficulty = "Hard",
            questionText = "The difference between Compound Interest and Simple Interest on a sum of ₹10,000 for 2 years at 10% per annum is:",
            optionA = "₹50",
            optionB = "₹75",
            optionC = "₹100",
            optionD = "₹120",
            correctOption = 2,
            explanation = "For 2 years, difference CI - SI = P × (R/100)² = 10,000 × (10/100)² = 10,000 × (1/100) = ₹100."
        ),

        // Railway RRB
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Science",
            topic = "Physics",
            difficulty = "Easy",
            questionText = "What is the SI unit of electric current?",
            optionA = "Volt",
            optionB = "Watt",
            optionC = "Ampere",
            optionD = "Ohm",
            correctOption = 2,
            explanation = "The SI unit of electric current is Ampere (A), named after the French physicist André-Marie Ampère."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Science",
            topic = "Chemistry",
            difficulty = "Easy",
            questionText = "What is the chemical formula of baking soda?",
            optionA = "Na2CO3 (Sodium Carbonate)",
            optionB = "NaHCO3 (Sodium Bicarbonate)",
            optionC = "NaOH (Sodium Hydroxide)",
            optionD = "Ca(OH)2 (Calcium Hydroxide)",
            correctOption = 1,
            explanation = "Baking soda is Sodium Bicarbonate or Sodium Hydrogen Carbonate, having the chemical formula NaHCO3."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Science",
            topic = "Biology",
            difficulty = "Medium",
            questionText = "Which organelle is popularly referred to as the 'Powerhouse of the Cell'?",
            optionA = "Ribosome",
            optionB = "Mitochondria",
            optionC = "Lysosome",
            optionD = "Endoplasmic Reticulum",
            correctOption = 1,
            explanation = "Mitochondria generate most of the chemical energy needed to power cellular reactions in the form of ATP (adenosine triphosphate)."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Awareness",
            topic = "Indian Railways History",
            difficulty = "Easy",
            questionText = "In which year did the first passenger train run in India between Bombay (Bori Bunder) and Thane?",
            optionA = "1848",
            optionB = "1853",
            optionC = "1857",
            optionD = "1862",
            correctOption = 1,
            explanation = "India's first passenger train traversed a 34-km route between Bori Bunder (Mumbai) and Thane on 16 April 1853, driven by three steam locomotives: Sahib, Sindh, and Sultan."
        ),

        // General / All Exams
        QuestionEntity(
            examCategory = "General",
            subject = "Current Affairs & Economics",
            topic = "Digital Public Infrastructure",
            difficulty = "Easy",
            questionText = "UPI (Unified Payments Interface) was developed by which organization in India?",
            optionA = "State Bank of India",
            optionB = "National Payments Corporation of India (NPCI)",
            optionC = "Reserve Bank Information Technology (ReBIT)",
            optionD = "NITI Aayog",
            correctOption = 1,
            explanation = "UPI was launched in 2016 by the National Payments Corporation of India (NPCI), an initiative of the RBI and Indian Banks' Association (IBA)."
        ),
        QuestionEntity(
            examCategory = "General",
            subject = "History",
            topic = "Ancient Indian History",
            difficulty = "Medium",
            questionText = "Which Mauryan Emperor renounced warfare and embraced Buddhism following the devastating Kalinga War?",
            optionA = "Chandragupta Maurya",
            optionB = "Bindusara",
            optionC = "Ashoka the Great",
            optionD = "Dasharatha Maurya",
            correctOption = 2,
            explanation = "Emperor Ashoka embraced Buddhism and the policy of 'Dhamma-vijaya' (victory through righteousness) after witnessing the colossal carnage of the Kalinga War in 261 BCE."
        ),
        QuestionEntity(
            examCategory = "General",
            subject = "Reasoning Ability",
            topic = "Direction Sense",
            difficulty = "Medium",
            questionText = "A person walks 5 km North, turns right and walks 12 km. What is the shortest distance from their starting position?",
            optionA = "13 km",
            optionB = "15 km",
            optionC = "17 km",
            optionD = "10 km",
            correctOption = 0,
            explanation = "Using Pythagoras theorem: Distance = √(5² + 12²) = √(25 + 144) = √169 = 13 km."
        ),
        QuestionEntity(
            examCategory = "General",
            subject = "General Science",
            topic = "Optics",
            difficulty = "Medium",
            questionText = "Which phenomenon explains the blue color of the sky during daytime?",
            optionA = "Total Internal Reflection",
            optionB = "Rayleigh Scattering of light",
            optionC = "Dispersion of light through droplets",
            optionD = "Interference of light waves",
            correctOption = 1,
            explanation = "Rayleigh scattering occurs when atmospheric molecules scatter shorter blue wavelengths of sunlight much more strongly than longer red wavelengths."
        ),
        // Additional High-Yield Practice Questions
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Polity",
            topic = "Constitutional Amendments",
            difficulty = "Hard",
            questionText = "Which Article of the Constitution of India deals with the power of Parliament to amend the Constitution and its procedure?",
            optionA = "Article 352",
            optionB = "Article 356",
            optionC = "Article 360",
            optionD = "Article 368",
            correctOption = 3,
            explanation = "Article 368 in Part XX of the Constitution provides Parliament the power to amend the Constitution by way of addition, variation or repeal in accordance with the specified procedure."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Polity",
            topic = "Local Self Government",
            difficulty = "Medium",
            questionText = "Which Constitutional Amendment Act accorded constitutional status to Panchayati Raj Institutions in India?",
            optionA = "71st Amendment Act",
            optionB = "73rd Amendment Act, 1992",
            optionC = "74th Amendment Act, 1992",
            optionD = "86th Amendment Act, 2002",
            correctOption = 1,
            explanation = "The 73rd Constitutional Amendment Act, 1992 added Part IX and the 11th Schedule containing 29 functional items for Panchayats."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "History",
            topic = "Ancient India - Indus Valley Civilization",
            difficulty = "Medium",
            questionText = "At which Indus Valley Civilization site was an artificial tidal dockyard discovered?",
            optionA = "Harappa",
            optionB = "Mohenjo-daro",
            optionC = "Lothal",
            optionD = "Kalibangan",
            correctOption = 2,
            explanation = "Lothal in Gujarat had a massive tidal brick basin identified as a dockyard connecting the settlement to the ancient course of the Bhogavo River and Arabian Sea."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Geography",
            topic = "Drainage System of India",
            difficulty = "Medium",
            questionText = "Under what name does the Brahmaputra River originate and flow in Tibet before entering India?",
            optionA = "Yarlung Tsangpo",
            optionB = "Mekong",
            optionC = "Salween",
            optionD = "Yangtze",
            correctOption = 0,
            explanation = "The Brahmaputra originates from the Chemayungdung glacier in Tibet, where it flows eastward as the Yarlung Tsangpo before making a U-turn around Namcha Barwa into Arunachal Pradesh."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Economy",
            topic = "Taxation & GST",
            difficulty = "Medium",
            questionText = "Which Constitutional Amendment Act introduced the Goods and Services Tax (GST) in India?",
            optionA = "99th Amendment Act",
            optionB = "100th Amendment Act",
            optionC = "101st Amendment Act, 2016",
            optionD = "103rd Amendment Act",
            correctOption = 2,
            explanation = "The 101st Constitutional Amendment Act, 2016 paved the way for the nationwide rollout of the Goods and Services Tax (GST) on 1 July 2017."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Environment & Ecology",
            topic = "Wildlife Conservation",
            difficulty = "Easy",
            questionText = "In which year was 'Project Tiger' launched in India to promote tiger conservation?",
            optionA = "1968",
            optionB = "1973",
            optionC = "1980",
            optionD = "1992",
            correctOption = 1,
            explanation = "Project Tiger was initiated on April 1, 1973 from Jim Corbett National Park during Prime Minister Indira Gandhi's tenure."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Quantitative Aptitude",
            topic = "Number System & Divisibility",
            difficulty = "Medium",
            questionText = "What is the least number which when divided by 6, 9, 12, 15, and 18 leaves a remainder of 2 in each case?",
            optionA = "178",
            optionB = "182",
            optionC = "180",
            optionD = "184",
            correctOption = 1,
            explanation = "LCM of (6, 9, 12, 15, 18) = 180. Required number = LCM + remainder = 180 + 2 = 182."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Quantitative Aptitude",
            topic = "Ratio & Proportion",
            difficulty = "Easy",
            questionText = "If A : B = 3 : 4 and B : C = 8 : 9, then what is A : C?",
            optionA = "1 : 2",
            optionB = "2 : 3",
            optionC = "3 : 4",
            optionD = "1 : 3",
            correctOption = 1,
            explanation = "A/C = (A/B) × (B/C) = (3/4) × (8/9) = (3 × 8) / (4 × 9) = 24/36 = 2/3. Thus A:C = 2:3."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Reasoning Ability",
            topic = "Blood Relations",
            difficulty = "Medium",
            questionText = "Pointing to a photograph, a man said, 'His mother is the only daughter of my mother.' Whose photograph was it?",
            optionA = "His brother's son",
            optionB = "His nephew",
            optionC = "His son",
            optionD = "His father",
            correctOption = 1,
            explanation = "The only daughter of the speaker's mother is his sister. Since the photo's mother is the speaker's sister, the photograph is of his nephew (or his sister's son)."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "English Language",
            topic = "One Word Substitution",
            difficulty = "Easy",
            questionText = "A person who loves, supports, and defends his country is called a:",
            optionA = "Traitor",
            optionB = "Patriot",
            optionC = "Connoisseur",
            optionD = "Philanthropist",
            correctOption = 1,
            explanation = "A 'Patriot' is a person who vigorously supports their country and is prepared to defend it against enemies."
        ),
        QuestionEntity(
            examCategory = "Banking",
            subject = "Banking Awareness",
            topic = "Non-Performing Assets (NPA)",
            difficulty = "Medium",
            questionText = "In Indian commercial banking, a loan account is classified as a Non-Performing Asset (NPA) if interest or principal remains overdue for more than:",
            optionA = "30 days",
            optionB = "60 days",
            optionC = "90 days",
            optionD = "180 days",
            correctOption = 2,
            explanation = "As per RBI prudential norms, an asset becomes non-performing when it ceases to generate income, typically if dues remain unpaid for over 90 days."
        ),
        QuestionEntity(
            examCategory = "Banking",
            subject = "Banking Awareness",
            topic = "Monetary Policy Committee",
            difficulty = "Medium",
            questionText = "How many members constitute the Monetary Policy Committee (MPC) of India?",
            optionA = "4 members",
            optionB = "5 members",
            optionC = "6 members",
            optionD = "8 members",
            correctOption = 2,
            explanation = "The MPC consists of 6 members: 3 from RBI (including the Governor as ex-officio chairperson) and 3 external members appointed by the Government of India."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Science",
            topic = "Sound & Waves",
            difficulty = "Easy",
            questionText = "What is the speed of sound waves in a vacuum?",
            optionA = "332 m/s",
            optionB = "3 × 10⁸ m/s",
            optionC = "0 m/s (Cannot travel in vacuum)",
            optionD = "1480 m/s",
            correctOption = 2,
            explanation = "Sound is a mechanical longitudinal wave that requires a material medium (solid, liquid, or gas) to propagate. It cannot travel through a vacuum."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Science",
            topic = "Vitamins & Health",
            difficulty = "Easy",
            questionText = "Deficiency of Vitamin C leads to which disease?",
            optionA = "Rickets",
            optionB = "Beriberi",
            optionC = "Scurvy",
            optionD = "Night Blindness",
            correctOption = 2,
            explanation = "Scurvy is caused by Vitamin C (Ascorbic acid) deficiency, resulting in bleeding gums, weakness, and skin hemorrhages."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Awareness",
            topic = "Modern Trains",
            difficulty = "Easy",
            questionText = "What was the indigenous project code name under which the 'Vande Bharat Express' train sets were designed and built by ICF Chennai?",
            optionA = "Train 18",
            optionB = "Gati 20",
            optionC = "Bharat Express 1",
            optionD = "Project Tejas",
            correctOption = 0,
            explanation = "Vande Bharat Express was originally developed as 'Train 18' by the Integral Coach Factory (ICF) in Chennai under the Make in India initiative."
        )
    )


    // Additional pack when user taps "Sync New Questions"
    val syncPackQuestions = listOf(
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Polity",
            topic = "Emergency Provisions",
            difficulty = "Hard",
            questionText = "Under Article 352, on what grounds can the President of India declare a National Emergency?",
            optionA = "War, External Aggression, or Armed Rebellion",
            optionB = "War, External Aggression, or Internal Disturbance",
            optionC = "Financial Instability and Foreign Invasion",
            optionD = "Failure of Constitutional Machinery in States",
            correctOption = 0,
            explanation = "By the 44th Amendment Act 1978, the phrase 'Internal Disturbance' was replaced with 'Armed Rebellion'. Thus Article 352 permits emergency on War, External Aggression, or Armed Rebellion."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "Reasoning Ability",
            topic = "Coding-Decoding",
            difficulty = "Easy",
            questionText = "If DELHI is coded as CCIDD, how is BOMBAY coded following the same subtraction pattern (-1, -2, -3, -4, -5)?",
            optionA = "AMJXVS",
            optionB = "AMLXVT",
            optionC = "AMJXVT",
            optionD = "BMKYWT",
            correctOption = 0,
            explanation = "B(-1)=A, O(-2)=M, M(-3)=J, B(-4)=X, A(-5)=V, Y(-6)=S. Hence AMJXVS."
        ),
        QuestionEntity(
            examCategory = "Banking",
            subject = "Banking Awareness",
            topic = "Financial Inclusion",
            difficulty = "Medium",
            questionText = "What does 'PMJDY' stand for in India's flagship financial inclusion program?",
            optionA = "Pradhan Mantri Jan Dhan Yojana",
            optionB = "Prime Minister Joint Development Yojana",
            optionC = "Pradhan Mantri Jeevan Dhan Yojana",
            optionD = "Public Monetary Joint Deposit Yojana",
            correctOption = 0,
            explanation = "PMJDY (Pradhan Mantri Jan Dhan Yojana) was launched in August 2014 to ensure universal banking access with zero balance accounts."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Science",
            topic = "Human Physiology",
            difficulty = "Medium",
            questionText = "Which blood group is universally known as the 'Universal Donor'?",
            optionA = "AB Positive",
            optionB = "AB Negative",
            optionC = "O Negative",
            optionD = "O Positive",
            correctOption = 2,
            explanation = "O Negative blood lacks A, B antigens as well as the Rh factor, allowing it to be safely transfused to individuals of any ABO and Rh blood group."
        ),
        QuestionEntity(
            examCategory = "UPSC",
            subject = "Indian Polity",
            topic = "Presidential Election",
            difficulty = "Hard",
            questionText = "Who among the following does NOT participate in the election of the President of India?",
            optionA = "Elected members of Lok Sabha",
            optionB = "Elected members of Rajya Sabha",
            optionC = "Elected members of State Legislative Assemblies",
            optionD = "Nominated members of Parliament and State Assemblies",
            correctOption = 3,
            explanation = "Under Article 54, the Electoral College for the President consists ONLY of elected members of both houses of Parliament and elected members of Legislative Assemblies of States/UTs. Nominated members do not participate."
        ),
        QuestionEntity(
            examCategory = "SSC CGL",
            subject = "English Language",
            topic = "Idioms & Phrases",
            difficulty = "Easy",
            questionText = "What does the idiom 'Bite the bullet' mean?",
            optionA = "To act aggressively without reason",
            optionB = "To face a grim situation with fortitude and courage",
            optionC = "To waste precious time",
            optionD = "To defeat an opponent decisively",
            correctOption = 1,
            explanation = "'Bite the bullet' means to bravely face an inevitable, difficult or unpleasant situation."
        ),
        QuestionEntity(
            examCategory = "Banking",
            subject = "Quantitative Aptitude",
            topic = "Simple Interest",
            difficulty = "Easy",
            questionText = "At what rate percent per annum will a sum of money double itself in 8 years at Simple Interest?",
            optionA = "10%",
            optionB = "12.5%",
            optionC = "15%",
            optionD = "8%",
            correctOption = 1,
            explanation = "For sum P to double, SI = P. Formula: SI = (P × R × T)/100 => P = (P × R × 8)/100 => R = 100/8 = 12.5%."
        ),
        QuestionEntity(
            examCategory = "Railways",
            subject = "General Science",
            topic = "Optics & Mirrors",
            difficulty = "Medium",
            questionText = "Which type of mirror is used as a rear-view mirror in motor vehicles?",
            optionA = "Concave mirror",
            optionB = "Convex mirror",
            optionC = "Plane mirror",
            optionD = "Cylindrical mirror",
            correctOption = 1,
            explanation = "Convex mirrors always produce an erect, diminished virtual image and have a wide field of view, making them ideal rear-view mirrors."
        )
    )


    fun getSampleSlots(userId: Long): List<TimetableSlotEntity> = listOf(
        TimetableSlotEntity(
            userId = userId,
            dayOfWeek = "Daily",
            startTime = "06:30 AM",
            endTime = "08:00 AM",
            subject = "Current Affairs & Editorial Reading",
            topicNotes = "Read The Hindu / Indian Express editorials & take bullet notes",
            isReminderEnabled = true,
            isCompletedToday = true
        ),
        TimetableSlotEntity(
            userId = userId,
            dayOfWeek = "Daily",
            startTime = "09:30 AM",
            endTime = "11:30 AM",
            subject = "Core Subject (Polity / GS / Quant)",
            topicNotes = "Deep dive into High Yield Topics + solve past year questions",
            isReminderEnabled = true,
            isCompletedToday = false
        ),
        TimetableSlotEntity(
            userId = userId,
            dayOfWeek = "Daily",
            startTime = "03:00 PM",
            endTime = "04:30 PM",
            subject = "Daily Mock Test Practice",
            topicNotes = "Take customized 15-20 question timed mock test on ExamPrep Pro",
            isReminderEnabled = true,
            isCompletedToday = false
        ),
        TimetableSlotEntity(
            userId = userId,
            dayOfWeek = "Daily",
            startTime = "08:00 PM",
            endTime = "09:30 PM",
            subject = "Revision & Notes Consolidation",
            topicNotes = "Review flagged quiz errors and read uploaded PDF chapters",
            isReminderEnabled = true,
            isCompletedToday = false
        )
    )

    fun getSampleNotes(userId: Long): List<StudyNoteEntity> = listOf(
        StudyNoteEntity(
            userId = userId,
            title = "Fundamental Rights (Articles 12-35) Quick Summary",
            subject = "Indian Polity",
            content = "1. Right to Equality (Art. 14-18)\n2. Right to Freedom (Art. 19-22)\n3. Right against Exploitation (Art. 23-24)\n4. Right to Freedom of Religion (Art. 25-28)\n5. Cultural and Educational Rights (Art. 29-30)\n6. Right to Constitutional Remedies (Art. 32) - Enforced via 5 writs: Habeas Corpus, Mandamus, Prohibition, Certiorari, Quo Warranto.",
            isPinned = true,
            sourcePdfName = "UPSC_Polity_Handnotes.pdf"
        ),
        StudyNoteEntity(
            userId = userId,
            title = "Speed Math & Percentage Shortcuts",
            subject = "Quantitative Aptitude",
            content = "Fraction to % cheat sheet:\n• 1/2 = 50%\n• 1/3 = 33.33%\n• 1/4 = 25%\n• 1/5 = 20%\n• 1/6 = 16.66%\n• 1/7 = 14.28%\n• 1/8 = 12.5%\n• 1/9 = 11.11%\n• 1/11 = 9.09%\n• 1/12 = 8.33%\n\nMultiplication by 11 trick: split first and last digits, sum adjacent pairs in between!",
            isPinned = true,
            sourcePdfName = "Quant_Formula_Booklet.pdf"
        ),
        StudyNoteEntity(
            userId = userId,
            title = "Indian Freedom Struggle Milestones (1915 - 1947)",
            subject = "History",
            content = "• 1915: Gandhiji returns from South Africa\n• 1917: Champaran Satyagraha (First Civil Disobedience)\n• 1919: Rowlatt Act & Jallianwala Bagh Massacre\n• 1920: Non-Cooperation Movement launched\n• 1922: Chauri Chaura incident (NCM suspended)\n• 1930: Dandi March & Civil Disobedience Movement\n• 1931: Gandhi-Irwin Pact & 2nd Round Table Conf.\n• 1942: Quit India Movement ('Do or Die')\n• 1947: Indian Independence Act.",
            isPinned = false,
            sourcePdfName = "History_Timeline.pdf"
        )
    )

    data class PeerCompetitor(
        val name: String,
        val targetExam: String,
        val xp: Int,
        val streak: Int,
        val accuracy: Int,
        val avatarId: Int,
        val isFriend: Boolean = true
    )

    val peerCompetitors = listOf(
        PeerCompetitor("Priya Sharma", "UPSC CSE", 2850, 18, 92, 1),
        PeerCompetitor("Aman Verma", "SSC CGL", 2420, 14, 88, 2),
        PeerCompetitor("Rohan Mehta", "Banking / IBPS", 2180, 12, 85, 3),
        PeerCompetitor("Neha Gupta", "UPSC CSE", 1950, 9, 89, 4),
        PeerCompetitor("Aditya Kapoor", "Railway RRB", 1720, 11, 81, 5),
        PeerCompetitor("Sneha Das", "SSC CGL", 1430, 7, 84, 6),
        PeerCompetitor("Kavya Patel", "Banking / IBPS", 1210, 5, 79, 7)
    )

    // Preloaded high-yield PDF/Document summaries for in-app reader
    data class PreloadedStudyGuide(
        val id: String,
        val title: String,
        val exam: String,
        val pagesCount: Int,
        val pages: List<String>
    )

    val preloadedGuides = listOf(
        PreloadedStudyGuide(
            id = "guide_polity",
            title = "Indian Polity High-Yield Formula Sheet",
            exam = "UPSC / SSC / State PSC",
            pagesCount = 3,
            pages = listOf(
                "PAGE 1: CONSTITUTIONAL FRAMEWORK\n\n• Borrowed Features:\n  - Parliamentary system, Rule of Law: UK\n  - Fundamental Rights, Judicial Review: USA\n  - DPSP, Presidential Election method: Ireland\n  - Emergency provisions: Germany (Weimar)\n  - Concurrent list, Joint sitting: Australia\n  - Amendment procedure: South Africa\n\n• Key Schedules:\n  - Schedule 1: States and UTs\n  - Schedule 7: Union, State, Concurrent Lists\n  - Schedule 8: 22 Official Languages\n  - Schedule 10: Anti-Defection Law (52nd Amd.)\n  - Schedule 11: Panchayati Raj (73rd Amd.)",
                "PAGE 2: KEY WRITS UNDER ARTICLE 32 & 226\n\n1. Habeas Corpus ('To have the body'): Against unlawful detention.\n2. Mandamus ('We command'): Directs public official to perform statutory duty.\n3. Prohibition ('To forbid'): Issued by higher court to lower court preventing jurisdictional overreach.\n4. Certiorari ('To be certified'): Quashes an illegal order passed by inferior court or tribunal.\n5. Quo-Warranto ('By what authority'): Inquires into legality of claim by a person to a public office.",
                "PAGE 3: PARLIAMENTARY TERMS & SESSIONS\n\n• Quorum: 1/10th of total membership of the House to conduct business.\n• Money Bill (Art. 110): Can only originate in Lok Sabha with prior recommendation of the President. Rajya Sabha can only delay for 14 days.\n• Casting Vote: Speaker has casting vote in case of an equality of votes (Art. 100).\n• Prorogation vs Adjournment:\n  - Adjournment terminates a sitting; done by Presiding Officer.\n  - Prorogation terminates a session; done by the President."
            )
        ),
        PreloadedStudyGuide(
            id = "guide_quant",
            title = "Speed Math & Quantitative Aptitude Compendium",
            exam = "SSC CGL / Banking / Railways",
            pagesCount = 3,
            pages = listOf(
                "PAGE 1: SPEED MULTIPLICATION & SQUARES\n\n• Squaring numbers ending with 5:\n  e.g., 65² = (6 × 7) | 25 = 4225\n  e.g., 85² = (8 × 9) | 25 = 7225\n\n• Base 100 Multiplication:\n  104 × 107 = (104 + 7) | (4 × 7) = 111 | 28 = 11128\n  96 × 93 = (96 - 7) | (-4 × -7) = 89 | 28 = 8928\n\n• Pythagorean Triplets to remember:\n  (3, 4, 5), (5, 12, 13), (7, 24, 25), (8, 15, 17), (9, 40, 41), (11, 60, 61), (12, 35, 37), (20, 21, 29).",
                "PAGE 2: TIME, SPEED & DISTANCE\n\n• Unit conversion: km/h to m/s = Multiply by 5/18\n• Average Speed for equal distances: (2 × S1 × S2) / (S1 + S2)\n• Relative speed:\n  - Same direction = (S1 - S2)\n  - Opposite direction = (S1 + S2)\n• Trains passing pole: Distance = Length of Train\n• Trains passing platform: Distance = Length of Train + Length of Platform",
                "PAGE 3: PROFIT, LOSS & DISCOUNT\n\n• Profit % = (Profit / CP) × 100\n• Loss % = (Loss / CP) × 100\n• Marked Price (MP) relation:\n  SP = MP × (100 - Discount%)/100\n• Single equivalent discount for d1% and d2%:\n  D = d1 + d2 - (d1 × d2)/100\n• If CP of x items = SP of y items:\n  Gain% = ((x - y)/y) × 100"
            )
        )
    )
}

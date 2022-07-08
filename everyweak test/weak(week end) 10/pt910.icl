module pt910
import StdEnv


:: University = ELTE | BME | Corvinus
:: Student = {name::String, uni :: University, grades:: [Int]}

Rose::Student
Rose = {name="Rose",uni=ELTE, grades =[5,5,3,4,2,4,5,5]}
Peter::Student
Peter = {name="Peter",uni=BME, grades =[3,2,3,4,2,4,2,1,4,3,2,4]}
Noah::Student
Noah = {name="Noah",uni=Corvinus,grades=[1,2,2,3,1,3,4,2,3,4,2,4,2,1]}
James::Student
James = {name="James",uni=ELTE,grades=[5,5,5,5,3,4,5,4,5]}
Lily::Student
Lily = {name="Lily",uni=BME,grades=[1,2,1,3,1,5,3,3,4,1,3,1,5,1,1]}
Harry::Student
Harry = {name="Harry",uni=Corvinus,grades=[3,4,1,3,4,2,3,5,5]}
Eros::Student
Eros = {name="Eros",uni=Corvinus,grades=[4,2,4,4,4,4,4,5,2]}
Isabella::Student
Isabella = {name="Isabella",uni=BME,grades=[5,5,5,4,5,5,4,5,4,5]}
Oliver::Student
Oliver = {name="Oliver",uni=ELTE,grades=[2,3,3,4,3,2,1,3,2,3]}




/*

1. Given an array of Student-s, return an array of triples which contain the name, univeristy, and gpa(average grade) of the

Student-s that have a gpa at least 3.5

*/



selectStudent :: {Student} ->{(String,University,Real)}
selectStudent arr = { (x.name,x.uni,((toReal (sum (x.grades))) / (toReal (length x.grades)))) \\ x <-: arr | ((toReal (sum (x.grades))) / (toReal (length x.grades))) >= 3.5 }

// Start = selectStudent {Rose,Harry,Isabella} // {("Rose",ELTE,4.125),("Isabella",BME,4.7)}
//Start = selectStudent {Oliver, Noah,James,Lily} // {("James",ELTE,4.55555555555556)}
//Start = selectStudent {Peter,Rose,Eros} // {("Rose",ELTE,4.125),("Eros",Corvinus,3.66666666666667)}
Start = selectStudent {Rose,Harry,Isabella,Oliver,James,Noah,Lily,Peter,Eros} // {("Rose",ELTE,4.125),("Isabella",BME,4.7),("James",ELTE,4.55555555555556),("Eros",Corvinus,3.66666666666667)}
//Start = selectStudent {Harry,Lily,Peter} // {}


///////////////////////////////////////////////////////


/*
2. Using the data provided above, return the students that are studing 
at ELTE and BME only
*/


bmelteStudents :: {Student} -> {Student}
bmelteStudents arr = { x \\ x <-: arr | (cond x.uni) }
where
    cond ELTE = True
    cond BME = True
    cond _ = False



/* The output of the terminal will be more detailed, 
   but for the sake of consiseness, I wrote only the name of the students.
 */
// Start = bmelteStudents {Harry, Isabella} // {Isabella}
// Start = bmelteStudents {Harry} // {}
//Start = bmelteStudents {Isabella} // {Isabella}
//Start = bmelteStudents {Rose,Harry,Isabella} // {Rose, Isabella}





let name="정현우"
let score=0

let person={
    name,
    score
}
console.log (person['name'], person.score)

//Destructuring
let person1={
    namE:"정미나",
    scorE:0
}

// let nameE = person1.name
// let scorE = person1['score']
//=>
let {namE,scorE} = person1
 console.log(namE,scorE)

let array = [1,2,3,4]

let [a,b] = array
console.log("a and b : ", a,b)

let [c,...rest] = array
console.log("c and d :", c)
console.log ("rest :", rest)
 //*Destructuring

//spead
let person2={
    name:"정누나",
    score:12
}

let person3 = {...person2/*,address:"cc" */ /*,name:"ff"*/} //객체를 하나 더 생성

let person4 = person2 //주소값 복사 referance copy
console.log(person3)
console.log(person4)
console.log(person2 == person3) //false
console.log(person2 == person4) //true

let a1 = [1,2]
let b1 = [...a1, 3]
console.log(b1)

let c1 = [...a1,...b1] //[1,2,3]
console.log(c1) //[1,2,1,2,3]

//삼항 연산자
let member ={name:"aa", age:17}
let member1 = null
if (member){
    console.log(person.name)
}
else {
    console.log("there is no person")
}

console.log(member1?member.name:"there is no person!")

let count =0
    let [count2, setCount2] = useState(3)
    const increament = () =>{
        setCount2(count2+1)
        count = count + 1 
        console.log("cout work?", count)
        console.log('count2', count2)
    }
    let name9="name"
    let score9=2
    let person9={
        name9,
        score9
    }
    
    console.log(person9)
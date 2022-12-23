import axios from "axios";
import React, {useEffect, useState} from "react";
import { useQuery } from "react-query";

var obj = []
function Check0() {
  const addShow = (e) => {
    console.log("누굴까",e.target.id)
    // fetch('/onLogin')
    let a = document.getElementById(e.target.id+"1");
    a.ariaExpanded = false
    console.log(a)
    axios.post ('/show/0/set', null,{
      params:{
        WHO: e.target.id,
    }
    })
  }

    const {data, status} = useQuery('getGame0Submits',  ()=>{
        fetch('/check/0',{
            method: "POST", //GET , POST, PUT, DELETE
        })
            .then ((res)=> res.json())
            .then ((data)=> {
                obj = []
                console.log("데어티aaaa",data)
                data.forEach(element => {
                    var a = JSON.parse(element)
                        obj.push (a)
                    console.log("옵젝트트", obj)
                });
            })
    },{
        enabled: true,
        cacheTime: Infinity,
    });
    console.log("상태", obj)
    console.log("데이터")
    if (status === 'loading'){
        console.log("로딩중")
    }
    if (status === 'error'){
        console.log ('에러 발생')
    }
    if (status === 'success'){
        console.log("성공")


    }














    
  
        const [isOpen, setMenu] = useState(false);  // 메뉴의 초기값을 false로 설정
        
        const toggleMenu = () => {
              setMenu(isOpen => !isOpen); // on,off 개념 boolean
          }






    useEffect(()=>{
        
    },[]);



    
    return (
            <main>

                
            {/* <div class="accordion accordion-flush" id="accordionFlushExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingOne">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
        Accordion Item #1
      </button>
    </h2>
    <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">Placeholder content for this accordion, which is intended to demonstrate the <code>.accordion-flush</code> class. This is the first item's accordion body.</div>
    </div>
  </div>
</div> */}



<div class="accordion accordion-flush" id="accordionFlushExample">
  
</div>
    {console.log("옵 개수수",obj.length)}


            {obj.map((item, i)=>{

                    return (<div class="accordion-item">
                    <h2 class="accordion-header collapsed" id="panelsStayOpen-headingOne">
                      <button id={item.NAME+"1"}key="fdd" class="btn btn-lg btn-primary" type="button" data-bs-toggle="collapse" data-bs-target={"#panelsStayOpen-"+String(i)} aria-controls={"#panelsStayOpen-"+String(i)}>
                        {item.NAME}
                      </button>
                    </h2>
                    <div id={"panelsStayOpen-"+String(i)} class="accordion-collapse collapse collapsed" aria-labelledby={"#panelsStayOpen-heading"+String(i)} data-bs-parent="#accordionFlushExample">
                      <div class="accordion-body">
                      {item.AFALSE === "1"? (<p style={{color:"red"}}>{item.FIRST}</p>):(<p>{item.FIRST}</p>)}
                      {item.AFALSE === "2"? (<p style={{color:"red"}}>{item.SECOND}</p>):(<p>{item.SECOND}</p>)}
                      {item.AFALSE === "3"? (<p style={{color:"red"}}>{item.THIRD}</p>):(<p>{item.THIRD}</p>)}
                     
                      </div>
                      <button class="btn btn-lg btn-primary" id={item.NAME}onClick={addShow}/>
                    </div>
                  </div>
                )
                })}
  
  


            </main>
    )
}

export default Check0;
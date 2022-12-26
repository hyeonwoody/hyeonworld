import React, { useEffect , useState} from "react";
import db from "../../db/games.json"
import Game0 from "../Games/0/Ranking0"
function Ranking(props){
    const [category, setCategory] = useState(props.game)

    function universal(){
        return (<p>univerasalaslasd</p>)
    }
    function game(category){
        
        switch (category){
            case 0:
                return <Game0/>

            default:
                return <h2>{db.games[category].name}</h2>
        }
            
    }

    const clickA = (e)=>{
        let id = e.target.getAttribute("id");
        console.log("클릭 값",id)
        setCategory(id)
    };
    useEffect(()=>{
        console.log("이펙")
    },[])
    return (
        <div>
            <ul class="nav">
                <li class="nav-item" key="fd">
                    <a className="nav-link active" key= "ff"aria-current="page" href="#" id={9999} onClick={clickA} style={{'font-size': "5px"}}>종합</a>
                </li>
            {db.games.map((item, i)=>{
                if (item.play){
                    return (<li class="nav-item">
                <a className="nav-link active" key="nique" aria-current="page" href="#" id={i} onClick={clickA} style={{'font-size': "5px"}}>{item.name}</a>
                    </li>)
                }
            })}
            
            </ul>
            <p>{category==='9999'?universal():game(parseInt(category))}</p>
        </div>
    )

    
}
export default Ranking;
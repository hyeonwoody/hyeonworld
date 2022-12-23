import React from "react";
import "./GenericShow.css"
import Game0 from "../Games/0/Show0"

function Show(props){
    function game(){
        
        switch (props.game){
            case 0:
                return <Game0/>

            default:
                return <div></div>
        }
            
    }
    return (
        <div>
            {game()}

        </div>
    )
}
export default Show;
import React from 'react';


const clickMe= ()=>{
        alert("리액트")
    }
function Box(props){
    return (
        <div classNmae="box">
            Box{props.num}
            {props.name}
            <button onClick={clickMe}>클릭</button>
        </div>
    )
}

export default Box
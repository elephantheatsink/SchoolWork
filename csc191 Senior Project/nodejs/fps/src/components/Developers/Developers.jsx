import React, { Component } from 'react'
import './Developers.css'

export class Developers extends Component {
    render() {
        return (
            <div className="trash" style={trashStyle}>
                <h1>Scrumbled Eggs</h1>
                <p style={{color: 'cornflowerblue'}}>Marina S. Testing</p>
                <p style={{color:'magenta'}}>Colleen G. Testing</p>
                <p style={{color:'lime', padding: '5px 30px'}}>Henway F. Testing</p>
                <p>Beomjin H. Testing1</p>
                <p>Sean F. Testing</p>
                <p>Brent P. Testing</p>
                <p>Hello World</p>
                <p>sausy</p>
             </div>

        )
    }
}
const trashStyle = {
    background: '#333'
}

export default Developers

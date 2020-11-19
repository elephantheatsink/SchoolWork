import React, { Component } from 'react'
import Header from './Header'

export class Test extends Component {
    render() {
        return (
            <React.Fragment>
                <Header/>
                <form action="/">
                    <input type="submit" value="Home" />
                </form>
            </React.Fragment>
        )
    }
}

export default Test

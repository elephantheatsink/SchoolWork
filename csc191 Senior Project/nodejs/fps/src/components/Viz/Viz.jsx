import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './Viz.css';

import draw from './d3'

export default class Viz extends Component {
    componentDidMount() {
        draw(this.props)
    }

    componentDidUpdate(prevProps) {
        draw(this.props)
    }
    
    render() {
        return (
            <div className="viz" />
        )
    }
}
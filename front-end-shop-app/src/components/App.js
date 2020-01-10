import React, {Component} from "react"
import "../styles/App.css";
import {BrowserRouter as Router} from "react-router-dom";
import Footer from "../layouts/Footer.js";
import Header from "./Header";
import Navigation from "../layouts/Navigation";
import Page from "../layouts/Page";
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {

    render() {
        return (
            <Router>
                <div className="app">
                    <header> {<Header/>}</header>
                    <main>
                        <aside className="navAside">{<Navigation/>}</aside>
                        <section className="page">{<Page/>}</section>
                    </main>
                    <footer>{<Footer/>}</footer>
                </div>
            </Router>
        )
    }
}

export default App;
import React, {Component, Fragment} from "react";
import "../styles/Header.css"

class Header extends Component {

    state = {
        searchValue: "",
        searchType: ""
    }

    options = ["Option1", "Option2", "Option3", "Option4", "Option5"];

    searchPlaceholder = "Type to search something...";

    handleSearchValue = (e) => {
        this.setState({
            searchValue: e.target.value
        })
    }

    handleSearchType = (e) => {
        this.setState({
            searchType: e.target.value
        })
    }

    render() {

        const options = this.options
        return (
            <Fragment>
                <form className="searchForm">
                    <label>
                        <input value={this.state.searchValue} className="searchBar" placeholder={this.searchPlaceholder}
                               onChange={this.handleSearchValue}
                               type="text"/>
                        <select className="searchSelect">
                            {options.map(o => <option key={o}>{o}</option>)}
                        </select>
                        <button className="searchButton">search</button>
                    </label>
                    <nav className="navbar navbar-default navbarHeader">
                        <div className="container-fluid">
                            <div className="navbar-header">
                                 <a className="navbar-brand" href="#">WebSiteName</a>
                            </div>
                            <ul className="nav navbar-nav">
                                <li className="active"><a href="#">Home</a></li>
                                <li><a href="#">Page 1</a></li>
                                <li><a href="#">Page 2</a></li>
                                <li><a href="#">Page 3</a></li>
                            </ul>
                        </div>
                    </nav>
                </form>

            </Fragment>
        )
    }
}

export default Header;
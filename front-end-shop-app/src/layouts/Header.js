import React, {Component, Fragment} from "react";
import "../styles/Header.css"

class Header extends Component {

    state = {
        searchValue: "",
        searchType: ""
    };

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
        return (
            <Fragment>
                <form className="searchForm">
                    <label>
                        <input value={this.state.searchValue} className="searchBar" placeholder={this.searchPlaceholder}
                               onChange={this.handleSearchValue}
                               type="text"/>
                        <select className="searchSelect">
                            <option> asa</option>
                        </select>
                        <button className="searchButton">search</button>
                    </label>
                </form>
            </Fragment>
        )
    }
}

export default Header;
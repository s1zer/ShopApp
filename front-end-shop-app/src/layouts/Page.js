import React from "react";
import {Route, Switch} from 'react-router-dom';
import User from "../pages/User"


const Page = () => {
    return (
        <>
            <Switch>
                <Route path="/user" component={User}/>
            </Switch>
        </>
    )
}

export default Page;

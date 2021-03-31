import React, {Component} from 'react';
import GamesListComponent from "./GamesListComponent";
import GameEditComponent from "./GameEditComponent";
import GameComponent from "./GameComponent";
import GameAddComponent from "./GameAddComponent"
import {BrowserRouter as Router, Link} from "react-router-dom";
import Switch from "react-router-dom/Switch";
import Route from "react-router-dom/Route";
import DeveloperComponent from "./DeveloperComponent";
import ReviewAddComponent from "./ReviewAddComponent";


class AppRouting extends Component {

    constructor(props) {
        super(props);

        this.state = {}

        this.goToHome = this.goToHome.bind(this);
    }

    goToHome() {
        this.props.history.push('games');
    }

    render() {
        return (
            <Router>
                <>
                    <h1><Link to={'/'} className="form-control-plaintext">Game Review Application</Link></h1>
                    <Switch>
                        <Route path="/" exact component={GamesListComponent} />
                       <Route path="/games" exact component={GamesListComponent} />
                       <Route path="/games/:id" component={GameComponent} />
                       <Route path="/games-add" exact component={GameAddComponent} />
                       <Route path="/games-edit/:id" component={GameEditComponent} />
                       <Route path="/developers/:id" component={DeveloperComponent} />
                       <Route path="/review/:id" component={ReviewAddComponent} />
                    </Switch>
                </>
            </Router>
        )
    }
}

export default AppRouting
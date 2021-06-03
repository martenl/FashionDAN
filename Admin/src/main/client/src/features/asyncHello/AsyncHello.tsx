import React, { useState } from 'react';
import styles from './AsyncHello.module.css';
import { gql, useLazyQuery } from '@apollo/client';

interface HelloVars {
    count: number;
}

interface HelloResult {
    hello: string
}

const GET_HELLO = gql`
  query GetHellos($count: Int!) {
    hello(count:$count)
  }
`;


export function AsyncHello() {
    const [helloCount, setHelloCount] = useState(5);
    const [lazyHello, setLazyHello] = useState(null);
    const [getHello, { loading, data }] = useLazyQuery(GET_HELLO);


    const loadingPlaceholder = "Loading ...";

    if(loading) return <p>{loadingPlaceholder}</p>;

    if (data && data.hello) {
        setLazyHello(data.hello);
    }

    return (
        <div>
            <div className={styles.row}>
                <span className={styles.value}>Result from GraphQL:
                </span>
                <input
                    className={styles.textbox}
                    aria-label="Set hello count"
                    value={helloCount}
                    onChange={e => setHelloCount(Number(e.target.value) || 1)}
                />
                <button
                    className={styles.asyncButton}
                    onClick={() => console.log(helloCount)/*getHello({ variables: { count: helloCount } })*/}
                >
                    Async Hello
                </button>

            </div>
        </div >
    );
}
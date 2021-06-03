import React, { useState } from 'react';
import styles from './Hello.module.css';
import { useQuery, gql } from '@apollo/client';

interface GetCampaignsVars {
    page: number;
}

interface GetCampaignResult {
    id: string,
    name: string,
    startDate: string,
    endDate: string
}

//185f4008-f9b6-c066-f345-d7c9e8c2b7bb

const GET_CAMPAIGNS = gql`
  query GetCampaigns($page: Int!) {
    getCampaigns(page:$page) [{
        id
        name
        startDate
        endDate
    }]
  }
`;


export function CampaignList() {
    const { loading, data } = useQuery<GetCampaignResult, GetCampaignsVars>(
        GET_CAMPAIGNS,
        { variables: { page: 0 } }
    );


    const loadingPlaceholder = "Loading ...";
    return (
        <div>

            <div className={styles.row}>
                <button
                    className={styles.button}
                    onClick={() => console.log('hello button')}>
                    Create new campaign
                </button>
            </div>
        </div >
    );
}
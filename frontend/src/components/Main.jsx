import Members from './Members'

export default function Main({summaryData}) {
    const members = summaryData.hasOwnProperty('members') ? summaryData.members : {}
    //const expenses = summaryData.hasOwnProperty('expenses') ? summaryData.expenses : {}
    const balances = summaryData.hasOwnProperty('balances') ? summaryData.balances : {}

    return (
            <main>
                <section className="flex-row">
                    <Members members={members} balances={balances}/>
                </section>
            </main>
        )
}
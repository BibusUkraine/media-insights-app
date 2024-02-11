/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            screens:{
                'sm':{'max':'700px'},
                'md':{
                    'min':'701px',
                    'max':'1000px'
                },
            },
            backgroundColor: {
                'primary': '#0E1217',
                'secondary': '#1C1F26',
                'tertiary': '#383D47',
                'quaternary': '#ed17d8',
                'quinary': '#ffffff',
            },
            backgroundImage: {
                'emptyImage': "linear-gradient(311deg, rgba(23,18,233,0.42) 0%, rgba(237,23,216,1) 100%);",
            },
            borderColor:{
                'primary':'#383D47'
            },
            animation: {
                'pulse-once': 'pulse 1s cubic-bezier(0.4, 0, 0.6, 1) 1',
            },
            fontSize:{
                '2xs': '0.6rem'
            }
        },
    },
    plugins: [],
}
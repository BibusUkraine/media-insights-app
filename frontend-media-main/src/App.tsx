import {useEffect, useState, useRef} from "react";
import {RootState} from "./store/store";
import {getAllNews} from './service/ApiService';
import {useSelector} from "react-redux";
import _throttle from "lodash/throttle"
import Cards from "./components/cards/Cards";
import Logo from "./components/ui/logo/Logo";
import SearchBar from "./components/ui/search-bar/SearchBar";
import FilterBar from "./components/ui/filter-bar/FilterBar";
import CircleButton from "./components/ui/html-code-sign/CircleButton";

import TestIcon2 from './assets/TestIcon2.webp'
import ImageOverview from './assets/imageOverview.jpeg'
import HtmlSign from './assets/htmlCodeSign.svg';
import Bot from './assets/bot.svg';
import BurgerMenu from "./components/ui/burger-menu/BurgerMenu";
import {Simulate} from "react-dom/test-utils";
import load = Simulate.load;


const topics = ['Ai', 'React', 'Java', 'JS', 'Weather', 'Politics', 'Music', 'Movies', 'Crypto', 'Tech-News', '.Net', 'Gaming', 'Machine-Learning', 'Testing', 'Database', 'Data-science']
// const cardInfo = [
//     {
//         id: '1',
//         logoLink: TestIcon2,
//         title: 'The future of AI',
//         topic: 'AI',
//         description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
//         publishDate: '2022-04-08T00:00:00.000Z',
//         url: 'https://www.google.com'
//     },
//     {
//         id: '2',
//         imageLink: ImageOverview,
//         title: 'asdasdasd',
//         topic: 'Machine-learning',
//         description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
//         publishDate: '2022-03-08T00:00:00.000Z',
//         url: 'https://dasdasd'
//     },
//     {
//         id: '3',
//         logoLink: TestIcon2,
//         imageLink: ImageOverview,
//         title: 'dasdascxc',
//         topic: 'Tech-learning',
//         description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
//         publishDate: '2022-02-08T00:00:00.000Z',
//         url: 'https://www.google.com'
//     },
//     {
//         id: '4',
//         logoLink: TestIcon2,
//         imageLink: ImageOverview,
//         title: 'Tsdssda',
//         topic: 'Crypto',
//         description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
//         publishDate: '2023-02-08T00:00:00.000Z',
//         url: 'https://www.google.com'
//     }
//     ,
//     {
//         id: '5',
//         logoLink: TestIcon2,
//         imageLink: ImageOverview,
//         title: 'The future of AI',
//         topic: 'AI',
//         description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
//         publishDate: '2022-04-08T00:00:00.000Z',
//         url: 'https://www.google.com'
//     }
// ]

function App() {
    const [clicked, setClicked] = useState(false);
    const [page, setPage] = useState(0)
    const [loadMore,setLoadMore] = useState(false)
    const [loading, setLoading] = useState(true);
    const date = new Date();
    const year = date.getFullYear();
    const [cardInfo, setCardInfo] = useState([]);
    const elRef = useRef<HTMLDivElement>(null)
    const selectedFilters = useSelector((state: RootState) => state.app.filter);

    const handleScroll = _throttle(() => {
        setLoadMore(false)
        const pageHeight = window.innerHeight;
        const footerTopPosition = elRef.current?.getBoundingClientRect().top;

        if (footerTopPosition && pageHeight - footerTopPosition > 30 && !loading) {
            setLoadMore(true)
            setPage((prevPage) => prevPage + 1);
        }
    }, 500);

    const onSearch = (e,input:string) =>{
        e.preventDefault()
        setPage(0)
        getAllNews(input,page).then((res) => {
            setLoading(false);
            setCardInfo(res)
        }).catch((err) => {
            console.log(err);
            setLoading(false);
        })
    }

    useEffect(() => {
        handleScroll();
        window.addEventListener("scroll", handleScroll);
        return () => {
            window.removeEventListener("scroll", handleScroll);
        };
    }, [loading]);

    useEffect(() => {
        if (clicked) {
            getAllNews(selectedFilters.join(',').toLowerCase(), page)
                .then((res) => {
                    setLoading(false);
                    setCardInfo((prevCardInfo) => [...prevCardInfo, ...res]);
                })
                .catch((err) => {
                    console.log(err);
                    setLoading(false);
                });
        }
    }, [clicked, page]);

    function formatDate(dateString: string) {
        const options = {month: 'short', day: 'numeric'} as const;
        const date = new Date(dateString);
        return date.toLocaleDateString('en-US', options);
    }



    return (
        <div className={'min-h-screen h-full relative overflow-hidden flex flex-col justify-between'}>
            <header className={'flex justify-center h-[80px]'}>
                <div className={'w-[95%] h-full flex items-center justify-between'}>
                    <div className={'flex w-[45%] h-[60%] items-center '}>
                        <Logo/>
                        <FilterBar topics={topics} setClicked={() => setClicked(!clicked)} clicked={clicked}/>
                        <CircleButton className={'sm:hidden'} link={'http://mediainsightsservice.northeurope.cloudapp.azure.com:8080/api/articles/feed'} image={HtmlSign}
                                      classImage={'rotate-[45deg] ml-1 mb-1 '}/>
                    </div>
                    <div className={'w-[40%] flex justify-end'}>
                        <CircleButton className={'sm:hidden '} link={'https://t.me/Mesight_bot'} image={Bot}
                                      classImage={'ml-[0.1rem]'}/>
                        <SearchBar className={'sm:hidden justify-center '} onSearch={onSearch}/>
                        <BurgerMenu/>
                    </div>
                </div>
            </header>
            <main className={'flex justify-center h-full my-3'}>
                <div className={'w-[95%] h-full flex flex-wrap justify-between md:justify-center sm:justify-center'}>
                    <div
                        className={'w-[95%] h-full flex flex-wrap justify-between md:justify-center sm:justify-center'}>
                        {loading ? (
                            // Display 8 placeholder cards when loading is true
                            Array.from({ length: 8 }, (_, index) => (
                                <Cards key={index} loading={loading} />
                            ))
                        ) : (
                            // Render actual cards when loading is false
                            cardInfo?.map((card, index) => (
                                <Cards key={index} card={{ ...card, publishDate: formatDate(card.publishDate) }} loading={loading} />
                            ))
                        )}
                        {
                            loadMore &&  <div className={'w-full flex justify-center'}>
                              <div className={'flex items-center'}>
                                  <p className={'text-white mr-2'}>Loading...</p>
                                  <svg className="animate-spin h-5 w-5 bg-quinary rounded" viewBox="0 0 24 24">
                                  </svg>
                              </div>
                            </div>
                        }
                    </div>
                </div>
            </main>
            <footer ref={elRef}>
                <div
                    className={'w-full h-[50px] bg-primary flex justify-center items-center border-t-2 border-primary '}>
                    <p className={'text-white'}>© {year} bibus.dev</p>
                </div>
            </footer>
        </div>
    )
}

export default App

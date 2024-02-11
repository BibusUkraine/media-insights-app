import {useState} from "react";
import Reply from '../../assets/reply.svg'
import Share from '../../assets/share.svg'
import Link from '../../assets/link.svg'
import styles from './Cards.module.css'


type CardInfo = {
    uuid: string,
    topic?: string | undefined,
    title: string,
    description: string,
    url: string,
    logoLink?: string | undefined,
    publishDate: string,
    imageLink?: string | undefined,

    loading: boolean
}

const Cards = ({card, loading}: CardInfo) => {
    const [reply, setReply] = useState(false)
    const [selectedCardId, setSelectedCardId] = useState('');
    const [coppied, setCoppied] = useState(false)

    const copyLinkToClipboard = (link: string) => {
        navigator.clipboard.writeText(link)
            .then(() => {
                setCoppied(true)
            })
            .catch((err) => {
                setCoppied(false)
                console.log('Something went wrong', err)
            });
    };

    return (
        <>
            <div
                className={`w-[250px] h-[350px] mb-8 mr-2 relative cursor-pointer ${styles.cardSide}`}>
                <div
                    className={`flex justify-center items-center w-full h-full bg-secondary border-primary border-2 rounded-md  ${styles.cardFront}`}>
                    <div className={'w-[85%] h-[95%] flex flex-col relative'}>
                        <div className={'flex h-[10%] items-center'}>
                            <div
                                className={`${card?.logoLink ? '' : 'bg-emptyImage'} rounded-full w-[32px] h-[32px] bg-center bg-contain mr-2`}>
                                {loading ?
                                    <div className={'animate-pulse bg-gray-300 rounded-full w-[32px] h-[32px] '}/> :
                                    card?.logoLink &&
                                    <img src={card?.logoLink} alt={'icon'} className={'rounded-full w-full h-full '}/>
                                }
                            </div>
                            <div className={'flex flex-col'}>
                                {loading ?
                                    <div
                                        className={'animate-pulse bg-gray-300 rounded-full w-[100px] h-[12px] mb-1'}/> :
                                    <h1 className={'text-sm text-white'}>bibus.dev group</h1>
                                }
                                {loading ?
                                    <div className={'animate-pulse bg-gray-300 rounded-full w-[50px] h-[12px] '}/> :
                                    <p className={'text-xs text-white'}>creator • {card.publishDate}</p>}
                            </div>
                        </div>
                        <div className={'mt-5 '}>
                            {loading ? (
                                <>
                                    <div className={'animate-pulse bg-gray-300 rounded w-[100%] h-[12px] my-1'}></div>
                                    <div className={'animate-pulse bg-gray-300 rounded w-[100%] h-[12px] my-1'}></div>
                                    <div className={'animate-pulse bg-gray-300 rounded w-[70%] h-[12px] my-1'}></div>
                                </>
                            ) : <h1 className={'font-bold text-white '}>{card.title}</h1>}
                        </div>
                        <div
                            className={`mt-6 h-[35%] flex justify-center items-center ${card?.imageLink && !loading ? '' : 'bg-emptyImage'} rounded`}>
                            {loading ? <div className={'animate-pulse bg-gray-300 rounded w-[100%] h-[100%] '}/> :
                                card.imageLink && <img src={card?.imageLink} alt={'image overview'}
                                                           className={'w-full h-full object-cover rounded'}/>

                            }
                        </div>
                        <div className={'h-[5%] w-full flex justify-end absolute bottom-0'}>
                            <img src={Reply} alt={'reply'} className={'min-h-[16px] min-w-[16px]'}/>
                        </div>
                    </div>
                </div>
                <div
                    className={`flex justify-center  items-center w-full h-full bg-secondary border-primary border-2 rounded  ${styles.cardBack}`}>
                    <div className={'w-[85%] h-[95%] flex flex-col relative '}>
                        <div className={`flex h-[10%] items-center ${reply && !loading ? 'blur-sm' : ''}`}>
                            <div
                                className={`${card?.logoLink ? '' : 'bg-emptyImage'} rounded-full w-[32px] h-[32px] bg-center bg-contain mr-2`}>
                                {loading ?
                                    <div className={'animate-pulse bg-gray-300 rounded-full w-[32px] h-[32px] '}/> :
                                    card?.logoLink &&
                                    <img src={card?.logoLink} alt={'icon'} className={'rounded-full w-full h-full '}/>
                                }
                            </div>
                            <div className={'flex flex-col'}>
                                {loading ?
                                    <div
                                        className={'animate-pulse bg-gray-300 rounded-full w-[100px] h-[12px] mb-1'}/> :
                                    <h1 className={'text-sm text-white'}>bibus.dev group</h1>
                                }
                                {loading ?
                                    <div className={'animate-pulse bg-gray-300 rounded-full w-[50px] h-[12px] '}/> :
                                    <p className={'text-xs text-white'}>creator • {card.publishDate}</p>}
                            </div>
                        </div>
                        <div className={`mt-5 ${reply && !loading ? 'blur-sm' : ''}`}>
                            {loading ? (
                                    <>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[100%] h-[12px] my-1'}></div>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[80%] h-[12px] my-1'}></div>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[100%] h-[12px] my-1'}></div>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[60%] h-[12px] my-1'}></div>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[100%] h-[12px] my-1'}></div>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[80%] h-[12px] my-1'}></div>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[100%] h-[12px] my-1'}></div>
                                        <div className={'animate-pulse bg-gray-300 rounded w-[60%] h-[12px] my-1'}></div>
                                    </>
                                ) :
                                <p className={'text-white text-sm font-bold'}>{card.description}</p>
                            }
                        </div>
                        {
                            reply && !loading && (
                                <button
                                    className={`${coppied && card.uuid === selectedCardId ? 'bg-emptyImage text-white' : 'bg-quinary'} duration-500 absolute  w-[60%] h-[30px] p-1 top-[40%] left-[25%] flex items-center justify-center rounded-full px-2`}
                                    onClick={() => {
                                        copyLinkToClipboard(card.url);
                                        setSelectedCardId(card.uuid);
                                    }}
                                  >
                                    {coppied && card.uuid === selectedCardId ? null :
                                        <img src={Link} alt={'link'} className={'w-[16px] h-[16px] mr-1'}/>}
                                    <p className={'text-sm'}>{
                                        coppied && card.uuid === selectedCardId ? 'Link coppied!' : 'Copy link'
                                    }</p>
                                </button>
                            )
                        }
                        <div className={'h-[8%] w-full flex justify-between absolute bottom-0 items-center'}>
                            {
                              !loading ? <a href={card.url} target={'_blank'} rel={'noreferrer noopener'}
                                             className={'flex text-sm  bg-quinary px-3 rounded py-1 items-center border-[2px] border-primary'}>
                                  <img src={Share} alt={'share icon'} className={'h-[16px] w-[16px] mr-1'}/>
                                  Read post
                              </a> : <div></div>
                            }
                            <img src={Reply} alt={'reply'} className={'h-[16px] w-[16px]'}
                                 onClick={() => setReply(!reply)}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Cards;
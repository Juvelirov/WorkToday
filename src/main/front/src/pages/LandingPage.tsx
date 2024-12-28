import { Button } from "@/components/ui/button";
import type { s } from "@/types";
import { useNavigate } from "react-router-dom";

export function LandingPage() {
  const navigate = useNavigate();

  return (
    <div>
      <section className="bg-[#E4C1FF] p-20">
        <div className="flex justify-evenly items-center gap-10">
          <div className="w-[620px]">
            <h1 className="text-7xl mb-10">
              Work<span className="text-[#8300E7]">Today</span>
            </h1>
            <p className="text-3xl">
              Мы стремимся соединить амбициозных студентов с работодателями,
              готовыми инвестировать в будущее своего бизнеса.
            </p>
          </div>
          <div className="w-96 h-96 bg-[#8300E7] rounded-3xl" />
        </div>
        <div className="flex justify-center">
          <Button className="bg-[#8300E7]" onClick={() => navigate("/signup")}>
            Зарегистрироваться
          </Button>
        </div>
      </section>
      <section className="relative">
        <div className="flex">
          <h1 className="absolute top-5 left-5 text-5xl">Стажёрам</h1>
          {[articlesD[0], articlesD[1], articlesD[2]].map((a) => (
            <Article1
              key={a.bgC}
              bgC={a.bgC}
              title={a.title}
              description={a.description}
            />
          ))}
        </div>
      </section>
      <section className="relative">
        <div className="flex">
          <h1 className="absolute top-5 left-5 text-5xl">Работодателям</h1>
          {[articlesD[3], articlesD[4], articlesD[5]].map((a) => (
            <Article2
              key={a.bgC}
              bgC={a.bgC}
              title={a.title}
              description={a.description}
            />
          ))}
        </div>
      </section>
    </div>
  );
}

interface Article {
  bgC: s;
  title: s;
  description: s;
}

function Article1(p: Article) {
  return (
    <article
      className={`flex flex-col justify-between items-center w-1/3 px-8 pt-32 pb-12 ${p.bgC} min-h-[720px]`}
    >
      <div>
        <h2 className="text-3xl text-center mb-4">{p.title}</h2>
        <p className="text-center max-w-96">{p.description}</p>
      </div>
      <div className="w-96 h-96 bg-[#E4C1FF] rounded-3xl" />
    </article>
  );
}

function Article2(p: Article) {
  return (
    <article
      className={`flex flex-col justify-start items-center w-1/3 px-8 pt-32 pb-12 ${p.bgC}`}
    >
      <div>
        <div className="max-w-96 h-96 bg-[#E4C1FF] rounded-3xl" />
        <h2 className="text-3xl text-center mb-3 mt-5">{p.title}</h2>
        <p className="text-center max-w-96">{p.description}</p>
      </div>
    </article>
  );
}

const articlesD: Article[] = [
  {
    bgC: "bg-[#1A80B2]",
    title: "Широкая база данных",
    description:
      "У нас есть доступ к большой базе данных студентов и выпускников, что позволяет находить лучших кандидатов для каждой компании.",
  },
  {
    bgC: "bg-[#C91888]",
    title: "Индивидуальный подход",
    description:
      "Мы учитываем особенности каждой компании и стажера, создавая оптимальные условия для сотрудничества.",
  },
  {
    bgC: "bg-[#9AE115]",
    title: "Партнёрские программы",
    description:
      "Работая с образовательными учреждениями, мы обеспечиваем доступ к свежим талантам и современным навыкам.",
  },
  {
    bgC: "bg-[#831FD2]",
    title: "Поиск стажёров",
    description:
      "Помогаем компаниям находить подходящих стажеров, соответствующих их требованиям и корпоративной культуре.",
  },
  {
    bgC: "bg-[#E49622]",
    title: "Курирование стажёров",
    description:
      "Предоставляем услуги наставников и кураторов, которые помогают интегрировать стажеров в рабочий процесс и обеспечивают поддержку на протяжении всей стажировки.",
  },
  {
    bgC: "bg-[#30EB9C]",
    title: "Оценка и обратная связь",
    description:
      "Предлагаем инструменты для оценки работы стажеров и получения обратной связи, что позволяет улучшать процесс обучения и адаптации.",
  },
];

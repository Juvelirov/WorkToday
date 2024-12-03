import { Button } from "@/components/ui/button";

export function LandingPage() {
  return (
    <div className="scroll-container">
      <section
        className="section"
        style={{ backgroundColor: "#320057", flexDirection: "column" }}
      >
        <div className="flex gap-10">
          <div className="w-[620px]">
            <h1 className="text-7xl mb-4">
              Work<span className="text-purple-900">Today</span>
            </h1>
            <p>
              Мы стремимся соединить амбициозных студентов с работодателями,
              готовыми инвестировать в будущее своего бизнеса.
            </p>
          </div>
          <div className="w-[400px] h-[400px] bg-slate-500 rounded-3xl" />
        </div>
        <div>
          <Button>Зарегистрироваться</Button>
        </div>
      </section>
      <section className="section" style={{ backgroundColor: "#ffffff" }}>
        Section 2
      </section>
      <section
        className="section relative"
        style={{ backgroundColor: "#1a535c" }}
      >
        <div className="grid grid-cols-3 h-full">
          <h1 className="absolute text-5xl">Стажёрам</h1>
          <article
            className="flex flex-col justify-center items-center p-6"
            style={{ backgroundColor: "#1A80B2" }}
          >
            <div className="grid grid-rows-2 items-center h-[600px]">
              <div className="flex flex-col justify-center items-center h-52">
                <h2 className="text-3xl text-center mb-4">
                  Широкая база данных
                </h2>
                <p className="text-center">
                  У нас есть доступ к большой базе данных студентов и
                  выпускников, что позволяет находить лучших кандидатов для
                  каждой компании.
                </p>
              </div>
              <div className="w-96 h-96 bg-slate-500 rounded-3xl justify-self-center" />
            </div>
          </article>
          <article
            className="flex flex-col justify-center items-center p-6"
            style={{ backgroundColor: "#C91888" }}
          >
            <div className="grid grid-rows-2 items-center h-[600px]">
              <div className="flex flex-col justify-center items-center h-52">
                <h2 className="text-3xl text-center mb-4">
                  Индивидуальный подход
                </h2>
                <p className="text-center">
                  Мы учитываем особенности каждой компании и стажера, создавая
                  оптимальные условия для сотрудничества.
                </p>
              </div>
              <div className="w-96 h-96 bg-slate-500 rounded-3xl justify-self-center" />
            </div>
          </article>
          <article
            className="flex flex-col justify-center items-center p-6"
            style={{ backgroundColor: "#9AE115" }}
          >
            <div className="grid grid-rows-2 items-center h-[600px]">
              <div className="flex flex-col justify-center items-center h-52">
                <h2 className="text-3xl text-center mb-4">
                  Партнёрские программы
                </h2>
                <p className="text-center">
                  Работая с образовательными учреждениями, мы обеспечиваем
                  доступ к свежим талантам и современным навыкам.
                </p>
              </div>
              <div className="w-96 h-96 bg-slate-500 rounded-3xl justify-self-center" />
            </div>
          </article>
        </div>
      </section>
      <section
        className="section relative"
        style={{ backgroundColor: "#1a535c" }}
      >
        <div className="grid grid-cols-3 h-full">
          <h1 className="absolute text-5xl">Работодателям</h1>
          <article
            className="flex flex-col justify-center items-center p-6"
            style={{ backgroundColor: "#831FD2" }}
          >
            <div className="grid grid-rows-2 items-center h-[600px]">
              <div className="w-96 h-96 bg-slate-500 rounded-3xl justify-self-center" />
              <div className="flex flex-col justify-center items-center h-52">
                <h2 className="text-3xl text-center mb-4">Поиск стажёров</h2>
                <p className="text-center">
                  Помогаем компаниям находить подходящих стажеров,
                  соответствующих их требованиям и корпоративной культуре.
                </p>
              </div>
            </div>
          </article>
          <article
            className="flex flex-col justify-center items-center p-6"
            style={{ backgroundColor: "#E49622" }}
          >
            <div className="grid grid-rows-2 items-center h-[600px]">
              <div className="w-96 h-96 bg-slate-500 rounded-3xl justify-self-center" />
              <div className="flex flex-col justify-center items-center h-52">
                <h2 className="text-3xl text-center mb-4">
                  Курирование стажёров
                </h2>
                <p className="text-center">
                  Предоставляем услуги наставников и кураторов, которые помогают
                  интегрировать стажеров в рабочий процесс и обеспечивают
                  поддержку на протяжении всей стажировки.
                </p>
              </div>
            </div>
          </article>
          <article
            className="flex flex-col justify-center items-center p-6"
            style={{ backgroundColor: "#30EB9C" }}
          >
            <div className="grid grid-rows-2 items-center h-[600px]">
              <div className="w-96 h-96 bg-slate-500 rounded-3xl justify-self-center" />
              <div className="flex flex-col justify-center items-center h-52">
                <h2 className="text-3xl text-center mb-4">
                  Оценка и обратная связь
                </h2>
                <p className="text-center">
                  Предлагаем инструменты для оценки работы стажеров и получения
                  обратной связи, что позволяет улучшать процесс обучения и
                  адаптации.
                </p>
              </div>
            </div>
          </article>
        </div>
      </section>

      <section className="section" style={{ backgroundColor: "#320057" }}>
        Section 5
      </section>
    </div>
  );
}
